package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.models.MeasurementesInTargetResponse;
import com.diabunity.diabunityapi.services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/ranking/{month}")
    public ResponseEntity getRankingByMonth(@PathVariable(value = "month") Integer month) {
        List<MeasurementesInTargetResponse> res = rankingService.getRankingByMonth(month);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
