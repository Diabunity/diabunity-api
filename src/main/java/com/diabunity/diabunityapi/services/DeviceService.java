package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.Device;
import com.diabunity.diabunityapi.repositories.DeviceRepository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    public List<Device> getDevices(String userId) {
        return deviceRepository.findByUserId(userId);
    }

    public boolean addOrUpdateDevice(String userId, Device device) {
        Device existingDevice = deviceRepository.findByUserIdAndDeviceId(userId, device.getDeviceId());
        boolean isNewDevice = false;

        if (existingDevice != null) {
            existingDevice.setTimestamp(LocalDateTime.now());
        } else {
            existingDevice = device;
            device.setUserId(userId);
            isNewDevice = true;
        }
        save(existingDevice);
        return isNewDevice;
    }
}
