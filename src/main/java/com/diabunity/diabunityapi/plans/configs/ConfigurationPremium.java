package com.diabunity.diabunityapi.plans.configs;

public class ConfigurationPremium implements IConfigurationPlan {
    private int MAX_POSTS_PER_DAY = 10;

    @Override
    public int getMaxPostsAllowedOfTheDay() {
        return MAX_POSTS_PER_DAY;
    }
}
