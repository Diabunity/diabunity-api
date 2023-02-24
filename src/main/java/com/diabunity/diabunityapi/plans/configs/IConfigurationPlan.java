package com.diabunity.diabunityapi.plans.configs;

import com.diabunity.diabunityapi.models.SubscriptionConfigs;
import java.util.List;

public interface IConfigurationPlan {
    List<SubscriptionConfigs> getConfigAccordingPlan();
    int getMaxPostsPerDay();
}
