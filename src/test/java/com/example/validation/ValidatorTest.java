package com.example.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

  @Test
  void testMaxLength() {
    boolean actual = new Validator("test").maxLength(5).validate();
    assertTrue(actual);

    actual = new Validator("test").maxLength(3).validate();
    assertFalse(actual);
  }

  @Test
  void testMinLength() {
    boolean actual = new Validator("test").minLength(5).validate();
    assertFalse(actual);

    actual = new Validator("test").minLength(3).validate();
    assertTrue(actual);
  }

  @Test
  void testPattern() {
    boolean condition = new Validator("test").pattern("^[a-zA-Z]+").validate();
    assertTrue(condition);
  }

  @Test
  void testMultipleValidations() {
    boolean condition = new Validator("test").maxLength(5).pattern("^[a-zA-Z]+").validate();
    assertTrue(condition);

    condition = new Validator("test").maxLength(2).pattern("^[a-zA-Z]+").validate();
    assertFalse(condition);
  }
}
