package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

public class Device {
    @Field
    @JsonProperty("user_id")
    private String userId;

    @Field
    @JsonProperty("device_id")
    private String deviceId;

    @Field
    @JsonProperty("os_version")
    private String osVersion;

    @Field
    private String brand;

    @Field
    private LocalDateTime timestamp;

    public Device(String deviceId, String userId, String osVersion, String brand) {
        this.deviceId = deviceId;
        this.userId = userId;
        this.osVersion = osVersion;
        this.brand = brand;
        this.timestamp = LocalDateTime.now();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
