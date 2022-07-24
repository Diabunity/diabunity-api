package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.models.Measurement;
import com.diabunity.diabunityapi.services.MeasurementService;

import java.util.List;

import com.diabunity.diabunityapi.services.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MeasurementController {

  @Autowired
  private UserAuthService userAuthService;

  @Autowired
  private MeasurementService measurementService;

  @PostMapping("/measurements")
  public Object createMeasurements(@RequestHeader(value="auth-token") String idToken,
                                                  @RequestBody List<Measurement> measurements) throws Exception {
    String uid = userAuthService.getUserIDFromAuthToken(idToken);

    measurements.forEach(m -> m.setUserId(uid));
    List<Measurement> res = measurementService.saveAll(measurements);

    return new ResponseEntity<>(res, HttpStatus.CREATED);
  }

}
