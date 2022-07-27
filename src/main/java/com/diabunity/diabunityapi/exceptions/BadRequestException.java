package com.diabunity.diabunityapi.exceptions;

import java.util.List;

public class BadRequestException extends Exception {

  List<String> causes;

  public BadRequestException(String message, List<String> causes) {
    super(message);
    this.causes = causes;
  }

  public List<String> getCauses() {
    return causes;
  }

  public void setCauses(List<String> causes) {
    this.causes = causes;
  }
}
