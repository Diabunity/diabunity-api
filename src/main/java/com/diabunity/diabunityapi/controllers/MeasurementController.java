package com.diabunity.diabunityapi.controllers;


import com.diabunity.diabunityapi.exceptions.InvalidUserTokenException;
import com.diabunity.diabunityapi.exceptions.NotFoundException;
import com.diabunity.diabunityapi.models.Measurement;
import com.diabunity.diabunityapi.models.MeasurementsResponse;
import com.diabunity.diabunityapi.models.User;
import com.diabunity.diabunityapi.services.MeasurementService;
import com.diabunity.diabunityapi.services.UserService;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

  @Autowired
  private UserService userService;

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

    Optional<User> user = userService.getUser(uid);

    if(user == null) {
      throw new NotFoundException("User not found");
    }

    final Double minGlucose = user.get().getGlucoseMin();
    final Double maxGlucose = user.get().getGlucoseMax();

    List<Measurement> measurements = measurementService.getAllByUserId(uid,
        LocalDateTime.ofInstant(from.toInstant(),
            ZoneId.systemDefault()),
        LocalDateTime.ofInstant(to.toInstant(),
            ZoneId.systemDefault()));

    MeasurementsResponse response = new MeasurementsResponse(measurements, null);

    if (!measurements.isEmpty()) {
      measurementService.setMeasurementsStatus(measurements, minGlucose, maxGlucose);
      response.setAvg(measurementService.getMeasurementAVG(measurements, minGlucose, maxGlucose));
    }

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
