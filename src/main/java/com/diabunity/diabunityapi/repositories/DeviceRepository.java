package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.Device;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceRepository extends MongoRepository<Device, String> {
    Optional<Device> findByUserId(String userId);
}
