package com.diabunity.diabunityapi.controllers;


import com.diabunity.diabunityapi.exceptions.InvalidUserTokenException;
import com.diabunity.diabunityapi.models.MeasurementsReport;
import com.diabunity.diabunityapi.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
public class ReportsController {

    @Autowired
    private ReportsService reportsService;

    @GetMapping("/users/{id}/measurements/report")
    public ResponseEntity getMeasurementsDataForReportByUserId(HttpServletRequest request,
                                                               @PathVariable(value = "id") String uid,
                                                               @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                               @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) throws Exception {
        String authorizedUser = request.getSession().getAttribute("authorized_user").toString();
        if (!authorizedUser.equals(uid)) {
            throw new InvalidUserTokenException();
        }

        MeasurementsReport res = reportsService.getDataForMeasurementsReport(uid, from, to);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
