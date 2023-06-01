package com.diabunity.diabunityapi.repositories;

import com.diabunity.diabunityapi.models.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {
    Optional<Device> findByUserId(String userId);
}
