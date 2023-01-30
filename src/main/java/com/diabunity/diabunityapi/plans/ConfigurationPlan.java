package com.diabunity.diabunityapi.plans;

import com.diabunity.diabunityapi.plans.configs.ConfigurationFree;
import com.diabunity.diabunityapi.plans.configs.ConfigurationPremium;
import com.diabunity.diabunityapi.plans.configs.IConfigurationPlan;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationPlan {

    public ConfigurationPlan() {
    }

    public IConfigurationPlan getConfiguration(boolean isPremium) {
        if (isPremium) {
            return new ConfigurationPremium();
        }
        return new ConfigurationFree();
    }
}
