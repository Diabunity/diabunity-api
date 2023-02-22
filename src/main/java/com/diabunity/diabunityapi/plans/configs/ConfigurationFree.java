package com.diabunity.diabunityapi.plans.configs;

import com.diabunity.diabunityapi.models.SubscriptionConfigs;
import com.diabunity.diabunityapi.plans.ConfigurationKey;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationFree implements IConfigurationPlan {

    @Override
    public List<SubscriptionConfigs> getConfigAccordingPlan() {
        List<SubscriptionConfigs> result = new ArrayList<>();

        result.add(new SubscriptionConfigs(ConfigurationKey.MAX_POST_PER_DAY, 2));
        result.add(new SubscriptionConfigs(ConfigurationKey.MAX_HISTORY_MEASUREMENTS_DAYS, 7));
        result.add(new SubscriptionConfigs(ConfigurationKey.MAX_MEASUREMENT_SENSOR, 3));
        result.add(new SubscriptionConfigs(ConfigurationKey.MAX_MEASUREMENT_MANUAL, 3));

        return result;
    }


    @Override
    public int getMaxPostsPerDay() {
        return getConfigAccordingPlan()
                .stream().filter(config -> config.key.equals(ConfigurationKey.MAX_POST_PER_DAY)).findFirst().get().value;
    }

}
