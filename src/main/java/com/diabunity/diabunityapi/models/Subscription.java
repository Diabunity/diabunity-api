package com.diabunity.diabunityapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Field;

public class Subscription {

    @Field
    @JsonProperty("subscription_type")
    private SubscriptionType subscriptionType;

    @Field
    private List<SubscriptionConfigs> metadata;

    public Subscription(SubscriptionType subscriptionType, List<SubscriptionConfigs> metadata) {
        this.subscriptionType = subscriptionType;
        this.metadata = metadata;
    }

    public SubscriptionType getSubscriptionType() {
        // TODO: remove this when subscription plans are implemented
        return SubscriptionType.PREMIUM;
        // return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public List<SubscriptionConfigs> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<SubscriptionConfigs> metadata) {
        this.metadata = metadata;
    }
}
