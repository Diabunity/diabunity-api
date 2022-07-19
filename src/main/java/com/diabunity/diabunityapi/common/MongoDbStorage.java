package com.diabunity.diabunityapi.common;

import java.util.UUID;
import org.bson.json.JsonObject;

public class MongoDbStorage {

  public UUID save(final UUID key, final JsonObject value) {
    return key;
  }
}
