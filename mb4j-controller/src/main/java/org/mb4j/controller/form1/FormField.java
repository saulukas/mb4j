package org.mb4j.controller.form1;

import com.google.common.base.Strings;
import java.io.Serializable;

public class FormField implements Serializable {
  private static final long serialVersionUID = 1L;
  private String nameOrNull;
  private String valueOrNull;
  public final boolean required;

  private FormField(String value, boolean required) {
    this.valueOrNull = value;
    this.required = required;
  }

  public static FormField optionalField() {
    return optionalField(null);
  }

  public static FormField optionalField(String value) {
    return new FormField(value, false);
  }

  public static FormField requiredField() {
    return requiredField(null);
  }

  public static FormField requiredField(String value) {
    return new FormField(value, true);
  }

  public String name() {
    if (!hasName()) {
      throw new RuntimeException("Param name was not resolved for " + this);
    }
    return nameOrNull;
  }

  public String value() {
    return valueOrNull;
  }

  void resolveNameTo(String name) {
    this.nameOrNull = name;
  }

  public boolean hasName() {
    return !Strings.isNullOrEmpty(nameOrNull);
  }

  public void setValue(String value) {
    this.valueOrNull = value;
  }

  @Override
  public String toString() {
    return nameOrNull + "=[" + (required ? "required" : "optional") + ":" + valueOrNull + "]";
  }
}
