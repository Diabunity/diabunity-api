package com.diabunity.diabunityapi.plans.configs;

import com.diabunity.diabunityapi.models.SubscriptionConfigs;
import java.util.List;

public interface IConfigurationPlan {
    //HashMap<String, Integer> getConfigsAccordingPlan();
    List<SubscriptionConfigs> getConfigAccordingPlan();
    int getMaxPostsPerDay();
}
