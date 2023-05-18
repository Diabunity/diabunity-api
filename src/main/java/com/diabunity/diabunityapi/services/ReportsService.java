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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportsService {

    private final static int GLUCOSE_GAP_WARNING = 30;
    @Autowired
    private MeasurementRepository measurementRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthService userAuthService;

    public MeasurementsReport getDataForMeasurementsReport(String userID, LocalDateTime from, LocalDateTime to) throws Exception {
        Optional<User> user = userRepository.findById(userID);
        if (!user.isPresent()) {
            throw new UserNotFoundException("user not found");
        }

        User u = user.get();
        ReportsUserData userData = buildUserDataForReport(u);

        List<Measurement> measurements = measurementRepository.findAllByUserIdAndTimestampBetween(userID, from, to);

        List<ReportsMeasurementResult> results = measurements.stream()
                .map(m -> new ReportsMeasurementResult(m.getTimestamp(), m.getMeasurement()))
                .collect(Collectors.toList());

        ReportsMeasurementsMetadata metadata = buildMeasurementsMetadata(u, results);

        ReportsMeasurementsData measurementsData = new ReportsMeasurementsData(metadata, results);

        return new MeasurementsReport(userData, measurementsData);
    }

    private ReportsUserData buildUserDataForReport(User u) throws Exception {
        // fetch user from firebase to retrieve user´s name
        UserRecord userFirebase = userAuthService.getUser(u.getId());

        // calculate age
        LocalDate birthDate = u.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Long age = ChronoUnit.YEARS.between(birthDate, LocalDate.now());

        // TODO complete glucose info
        return new ReportsUserData(userFirebase.getDisplayName(), age.intValue(), u.getWeight().intValue(), u.getHeight().intValue(), u.getDiabetesType().toValue(), null);
    }

    private ReportsMeasurementsMetadata buildMeasurementsMetadata(User u, List<ReportsMeasurementResult> results) {
        int low = 0, inRange = 0, high = 0, hyper = 0;

        for (ReportsMeasurementResult r : results) {
            if (r.getValue() < u.getMinGlucose()) low++;
            else if (r.getValue() > (u.getMaxGlucose() + GLUCOSE_GAP_WARNING)) hyper++;
            else if (r.getValue() > u.getMaxGlucose()) high++;
            else inRange++;
        }

        return new ReportsMeasurementsMetadata(low, inRange, high, hyper);
    }
}
