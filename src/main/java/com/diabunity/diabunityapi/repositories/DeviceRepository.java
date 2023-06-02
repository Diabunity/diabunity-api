package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.Device;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceRepository extends MongoRepository<Device, String> {
    List<Device> findByUserId(String userId);
}
