package com.example.validation;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {

  private String string;
  private int integer;

  private boolean isOverMax = false;
  private boolean isUnderMin = false;
  private boolean doesNotMatchPattern = false;
  private boolean notAllowedValue = false;

  private final String DEFAULT_STR = "";

  public Validator(String input) {
    this.string = (input != null) ? input : DEFAULT_STR;
  }

  public Validator(int input) {
    this.integer = input;
  }

  public Validator maxLength(int max) {
    if (string.length() > max) {
      this.isOverMax = true;
      // TODO: log this
    }
    return this;
  }

  public Validator minLength(int min) {
    if (string.length() < min) {
      this.isUnderMin = true;
      // TODO: log this
    }
    return this;
  }

  public Validator pattern(String regex) {
    if (!Pattern.matches(regex, string)) {
      doesNotMatchPattern = true;
    }
    return this;
  }

  public Validator allowedValues(String... values) {
    List<String> allowedList = Arrays.asList(values);
    if (!allowedList.contains(string)) {
      notAllowedValue = true;
    }
    return this;
  }

  public boolean validate() {
    return !(isOverMax || isUnderMin || doesNotMatchPattern || notAllowedValue);
  }
}
