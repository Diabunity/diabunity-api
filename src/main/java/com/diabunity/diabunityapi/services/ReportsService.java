package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.auth.UserAuthService;
import com.diabunity.diabunityapi.exceptions.UserNotFoundException;
import com.diabunity.diabunityapi.models.*;
import com.diabunity.diabunityapi.repositories.MeasurementRepository;
import com.diabunity.diabunityapi.repositories.UserRepository;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class ReportsService {

    private final static int GLUCOSE_GAP_WARNING = 30;
    @Autowired
    private MeasurementRepository measurementRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthService userAuthService;

    public MeasurementsReport getDataForMeasurementsReport(String userID, LocalDateTime from, LocalDateTime to)
            throws Exception {
        Optional<User> user = userRepository.findById(userID);
        if (!user.isPresent()) {
            throw new UserNotFoundException("user not found");
        }

        User u = user.get();
        ReportsUserData userData = buildUserDataForReport(u);

        List<Measurement> measurements = measurementRepository.findAllByUserIdAndTimestampBetween(userID, from, to);

        Map<LocalDate, List<Measurement>> groupedMeasurements = measurements.stream()
                .collect(groupingBy(m -> m.getTimestamp().toLocalDate()));

        List<ReportsMeasurementResult> results = new ArrayList<>();
        for (Map.Entry<LocalDate, List<Measurement>> e : groupedMeasurements.entrySet()) {
            results.add(new ReportsMeasurementResult(e.getKey(),
                    e.getValue().stream()
                            .map(m -> new ReportsMeasurementResultData(m.getTimestamp(), m.getMeasurement()))
                            .collect(Collectors.toList())));
        }

        ReportsMeasurementsMetadata metadata = buildMeasurementsMetadata(u, measurements);

        ReportsMeasurementsData measurementsData = new ReportsMeasurementsData(metadata, results);

        return new MeasurementsReport(userData, measurementsData);
    }

    private ReportsUserData buildUserDataForReport(User u) throws Exception {
        // fetch user from firebase to retrieve user´s name
        UserRecord userFirebase = userAuthService.getUser(u.getId());

        // calculate age
        LocalDate birthDate = u.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Long age = ChronoUnit.YEARS.between(birthDate, LocalDate.now());

        GlucoseInfo glucoseInfo = new GlucoseInfo(
                new GlucoseRange(0D, u.getMinGlucose()),
                new GlucoseRange(u.getMinGlucose(), u.getMaxGlucose()),
                new GlucoseRange(u.getMaxGlucose(), u.getMaxGlucose() + GLUCOSE_GAP_WARNING),
                new GlucoseRange(u.getMaxGlucose() + GLUCOSE_GAP_WARNING, null));

        return new ReportsUserData(
                userFirebase.getDisplayName(),
                age.intValue(),
                Optional.ofNullable(u.getWeight()).orElse(0.0).intValue(),
                Optional.ofNullable(u.getHeight()).orElse(0.0).intValue(),
                Optional.ofNullable(u.getDiabetesType()).map(DiabetesType::toValue)
                        .orElse(DiabetesType.TYPE1.toValue()),
                glucoseInfo);
    }

    private ReportsMeasurementsMetadata buildMeasurementsMetadata(User u, List<Measurement> measurements) {
        int low = 0, inRange = 0, high = 0, hyper = 0;

        for (Measurement m : measurements) {
            if (m.getMeasurement() < u.getMinGlucose())
                low++;
            else if (m.getMeasurement() > (u.getMaxGlucose() + GLUCOSE_GAP_WARNING))
                hyper++;
            else if (m.getMeasurement() > u.getMaxGlucose())
                high++;
            else
                inRange++;
        }

        return new ReportsMeasurementsMetadata(low, inRange, high, hyper);
    }
}
