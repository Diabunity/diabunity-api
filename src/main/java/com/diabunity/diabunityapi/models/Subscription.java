package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Field;

public class Subscription {

    @Field
    @JsonProperty("subscription_type")
    private SubscriptionType subscriptionType;

    @Field
    private List<SubscriptionConfigs> configs;

    public Subscription(SubscriptionType subscriptionType, List<SubscriptionConfigs> configs) {
        this.subscriptionType = subscriptionType;
        this.configs = configs;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public List<SubscriptionConfigs> getConfigs() {
        return configs;
    }

    public void setConfigs(List<SubscriptionConfigs> configs) {
        this.configs = configs;
    }
}
