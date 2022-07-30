package com.diabunity.diabunityapi.controllers;

import static java.text.MessageFormat.format;

import com.diabunity.diabunityapi.exceptions.InvalidUserTokenException;
import com.diabunity.diabunityapi.exceptions.NotFoundException;
import com.diabunity.diabunityapi.models.Measurement;
import com.diabunity.diabunityapi.services.MeasurementService;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                                                   @RequestParam("from") @DateTimeFormat(pattern="yyyyMMdd") Date from,
                                                   @RequestParam("to")  @DateTimeFormat(pattern="yyyyMMdd") Date to) throws Exception {

    String authorizedUser = request.getSession().getAttribute("authorized_user").toString();
    if (!authorizedUser.equals(uid)) {
      throw new InvalidUserTokenException();
    }

    List<Measurement> measurements = measurementService.getAllByUserId(uid,
        LocalDateTime.ofInstant(from.toInstant(),
            ZoneId.systemDefault()),
        LocalDateTime.ofInstant(to.toInstant(),
            ZoneId.systemDefault()));

    if (measurements == null || measurements.isEmpty()) {
      throw new NotFoundException(format("Measurements not found with user id: {0} and date between {1} and {2}", uid, from, to));
    }

    return new ResponseEntity<>(measurements, HttpStatus.OK);
  }

}
