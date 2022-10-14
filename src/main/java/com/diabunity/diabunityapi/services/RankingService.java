package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.auth.UserAuthService;
import com.diabunity.diabunityapi.models.MeasurementsInTarget;
import com.diabunity.diabunityapi.models.RankingResponse;
import com.diabunity.diabunityapi.repositories.MeasurementsInTargetRepository;
import com.google.firebase.auth.UserRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RankingService {
    private final Logger logger = LogManager.getLogger(RankingService.class);
    @Autowired
    private MeasurementsInTargetRepository inTargetRepository;

    @Autowired
    private UserAuthService userAuthService;

    public RankingResponse getRankingByMonth(String providedUserID, Integer month) throws Exception {
        List<MeasurementsInTarget> usersInTarget = inTargetRepository.findByMonth(month);

        RankingResponse rankingResponse = new RankingResponse();
        List<RankingResponse.RankedUser> users = usersInTarget.stream().map(i -> {
            try {
                UserRecord user = userAuthService.getUser(i.getUserID());
                Integer inTargetPercentage = i.getMeasurementsInTarget() * 100 / i.getTotalMeasurements();
                return rankingResponse.new RankedUser(i.getUserID(), user.getDisplayName(), user.getPhotoUrl(), inTargetPercentage);
            } catch (Exception e) {
                logger.error("error building MeasurementesInTargetResponse", e);
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        users.sort(Comparator.comparingInt(RankingResponse.RankedUser::getPercentage).reversed());
        rankingResponse.setRanking(users);

        Optional<RankingResponse.RankedUser> currentUser = rankingResponse.getRanking().stream().filter(u -> u.getUserID().equals(providedUserID)).findFirst();
        if (!currentUser.isPresent()) {
            return rankingResponse;
        }

        OptionalInt currentUserPosition = IntStream.range(0, users.size())
                .filter(i -> users.get(i).getUserID() == currentUser.get().getUserID())
                .findFirst();
        rankingResponse.setUserInfo(rankingResponse.new UserInfo(currentUserPosition.getAsInt()));
        return rankingResponse;
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
