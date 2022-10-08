package com.diabunity.diabunityapi.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Field;

public class MeasurementWrapper {

  @Field
  List<Measurement> measurements;

  public MeasurementWrapper(JsonObject measurements) {
    Type listType = new TypeToken<List<Measurement>>() {}.getType();
    this.measurements = new Gson().fromJson(measurements, listType);
  }

  public List<Measurement> getMeasurements() {
    return measurements;
  }

  public void setMeasurements(List<Measurement> measurements) {
    this.measurements = measurements;
  }
}
