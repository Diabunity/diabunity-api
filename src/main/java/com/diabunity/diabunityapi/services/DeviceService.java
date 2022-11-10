package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.Device;
import com.diabunity.diabunityapi.repositories.DeviceRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    public Optional<Device> getDevice(String userId) {
        return deviceRepository.findByUserId(userId);
    }
}
