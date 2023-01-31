package com.diabunity.diabunityapi.plans.configs;

public class ConfigurationFree implements IConfigurationPlan {
   private int MAX_POSTS_PER_DAY = 4;

    @Override
    public int getMaxPostsAllowedOfTheDay() {
        return MAX_POSTS_PER_DAY;
    }

}
