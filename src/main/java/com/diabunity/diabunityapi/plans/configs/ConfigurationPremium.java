package com.diabunity.diabunityapi.plans.configs;

import com.diabunity.diabunityapi.models.SubscriptionConfigs;
import com.diabunity.diabunityapi.plans.ConfigurationKey;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationPremium implements IConfigurationPlan {

    @Override
    public List<SubscriptionConfigs> getConfigAccordingPlan() {
        List<SubscriptionConfigs> result = new ArrayList<>();

        result.add(new SubscriptionConfigs(ConfigurationKey.MAX_POST_PER_DAY, 10));
        result.add(new SubscriptionConfigs(ConfigurationKey.MAX_HISTORY_MEASUREMENTS_DAYS, 180));
        result.add(new SubscriptionConfigs(ConfigurationKey.MAX_MEASUREMENT_SENSOR, 30));
        result.add(new SubscriptionConfigs(ConfigurationKey.MAX_MEASUREMENT_MANUAL, 30));

        return result;
    }

    @Override
    public int getMaxPostsPerDay() {
        return getConfigAccordingPlan()
                .stream().filter(config -> config.key.equals(ConfigurationKey.MAX_POST_PER_DAY)).findFirst().get().value;
    }

}
