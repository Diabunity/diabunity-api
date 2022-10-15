package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.models.RankingResponse;
import com.diabunity.diabunityapi.services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/ranking/{month}")
    public ResponseEntity getRankingByMonth(HttpServletRequest request, @PathVariable(value = "month") Integer month) throws Exception {
        String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

        RankingResponse res = rankingService.getRankingByMonth(authorizedUser, month);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
