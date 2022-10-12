package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.auth.UserAuthService;
import com.diabunity.diabunityapi.models.MeasurementesInTargetResponse;
import com.diabunity.diabunityapi.models.MeasurementsInTarget;
import com.diabunity.diabunityapi.repositories.MeasurementsInTargetRepository;
import com.google.firebase.auth.UserRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RankingService {
    private final Logger logger = LogManager.getLogger(RankingService.class);
    @Autowired
    private MeasurementsInTargetRepository inTargetRepository;

    @Autowired
    private UserAuthService userAuthService;

    public List<MeasurementesInTargetResponse> getRankingByMonth(Integer month) {
        List<MeasurementsInTarget> usersInTarget = inTargetRepository.findByMonth(month);

        List<MeasurementesInTargetResponse> res = usersInTarget.stream().map(i -> {
            try {
                UserRecord user = userAuthService.getUser(i.getUserID());
                Integer inTargetPercentage = i.getMeasurementsInTarget() * 100 / i.getTotalMeasurements();
                return new MeasurementesInTargetResponse(user.getDisplayName(), user.getPhotoUrl(), i.getMonth(), inTargetPercentage);
            } catch (Exception e) {
                logger.error("error building MeasurementesInTargetResponse", e);
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        res.sort(Comparator.comparingInt(MeasurementesInTargetResponse::getPercentage).reversed());
        return res;
    }

    public void updateMeasuresInTargetForUserInMonth(String userID, Integer month, Integer newInTargetMeasures, Integer newTotalMeasures) {
        Optional<MeasurementsInTarget> res = inTargetRepository.findByUserIDAndMonth(userID, month);

        if (!res.isPresent()) {
            inTargetRepository.save(new MeasurementsInTarget(userID, month, newInTargetMeasures, newTotalMeasures));
            return;
        }

        MeasurementsInTarget measurementsInTarget = res.get();

        measurementsInTarget.setMeasurementsInTarget(measurementsInTarget.getMeasurementsInTarget() + newInTargetMeasures);
        measurementsInTarget.setTotalMeasurements(measurementsInTarget.getTotalMeasurements() + newTotalMeasures);

        inTargetRepository.save(measurementsInTarget);
    }

}
