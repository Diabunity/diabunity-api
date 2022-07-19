package com.diabunity.diabunityapi.services;

import com.diabunity.diabunityapi.common.MongoDbStorage;
import java.util.UUID;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

  @Autowired
  private MongoDbStorage mongoDbStorage;

  public UUID create(final UUID id, final JsonObject userData) {

    UUID uuidSaveConfirmation = mongoDbStorage.save(id, userData);

    return uuidSaveConfirmation;
  }

}
