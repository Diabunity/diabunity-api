package com.diabunity.diabunityapi.controllers;


import com.diabunity.diabunityapi.exceptions.InvalidUserTokenException;
import com.diabunity.diabunityapi.models.Measurement;
import com.diabunity.diabunityapi.models.MeasurementsResponse;
import com.diabunity.diabunityapi.services.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class MeasurementController {

  @Autowired
  private MeasurementService measurementService;

  @PostMapping("/users/{id}/measurements")
  public Object createMeasurements(HttpServletRequest request,
                                   @PathVariable(value="id") String uid,
                                   @RequestBody List<Measurement> measurements) throws Exception {

    String authorizedUser = request.getSession().getAttribute("authorized_user").toString();
    if (!authorizedUser.equals(uid)) {
      throw new InvalidUserTokenException();
    }

    measurements.forEach(m -> m.setUserId(uid));
    List<Measurement> res = measurementService.saveAll(measurements);

    return new ResponseEntity<>(res, HttpStatus.CREATED);
  }

  @GetMapping("/users/{id}/measurements")
  public ResponseEntity getAllMeasurementsByUserId(HttpServletRequest request,
                                                   @PathVariable(value="id") String uid,
                                                   @RequestParam("from") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                   @RequestParam("to")  @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) throws Exception {
    String authorizedUser = request.getSession().getAttribute("authorized_user").toString();
    if (!authorizedUser.equals(uid)) {
      throw new InvalidUserTokenException();
    }

    MeasurementsResponse response = measurementService.getAllByUserId(uid, from, to);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
