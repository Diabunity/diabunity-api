package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.models.SubscriptionType;
import com.diabunity.diabunityapi.models.User;
import com.diabunity.diabunityapi.plans.ConfigurationPlan;
import com.diabunity.diabunityapi.plans.configs.IConfigurationPlan;
import com.diabunity.diabunityapi.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ConfigurationPlan configurationPlan;

  public User saveUser(final User newUser) {
    return userRepository.save(newUser);
  }

  public Optional<User> getUser(final String id) {
    Optional<User> user = userRepository.findById(id);

    if (user.isPresent()) {
      user.get().setVerified(VerifiedUserService.isVerified(id));
      setUserConfigsAccordingToPlan(user.get());
    }

    return user;
  }

  public void setUserConfigsAccordingToPlan(User user) {
    //TODO: Uncomment this line and remove the next one when the subscription logic is implemented
    // SubscriptionType userSubscriptionType = user.getSubscription().getSubscriptionType();
    SubscriptionType userSubscriptionType = SubscriptionType.PREMIUM;
    IConfigurationPlan plan = configurationPlan.getConfiguration(userSubscriptionType);
    user.getSubscription().setMetadata(plan.getConfigAccordingPlan());
  }

}
