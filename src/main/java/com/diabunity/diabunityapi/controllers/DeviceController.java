package com.diabunity.diabunityapi.controllers;

import com.diabunity.diabunityapi.exceptions.BadRequestException;
import com.diabunity.diabunityapi.exceptions.InvalidUserTokenException;
import com.diabunity.diabunityapi.models.Device;
import com.diabunity.diabunityapi.models.Post;
import com.diabunity.diabunityapi.services.DeviceService;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/users/{id}/device_id")
    public Object createDevice(HttpServletRequest request,
                             @PathVariable(value = "id") String uid,
                             @Valid @RequestBody Device device,
                             BindingResult errors) throws Exception {

        if (errors.hasErrors()) {
            throw new BadRequestException("Parameters required but not found",
                    errors.getAllErrors().stream().map(item -> item.getDefaultMessage()).collect(Collectors.toList()));
        }

        String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

        if (!authorizedUser.equals(uid)) {
            throw new InvalidUserTokenException();
        }

        device.setUserId(uid);
        Device deviceSaved = deviceService.save(device);
        return new ResponseEntity<>(deviceSaved, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}/device_id")
    public Object getPosts(HttpServletRequest request,
                           @PathVariable(value = "id") String uid) throws Exception {

        String authorizedUser = request.getSession().getAttribute("authorized_user").toString();

        if (!authorizedUser.equals(uid)) {
            throw new InvalidUserTokenException();
        }

        Optional<Device> deviceResponse = deviceService.getDevice(uid);

        return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
    }

}