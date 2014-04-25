package org.mb4j.controller.form1;

import com.google.common.base.Strings;
import java.io.Serializable;

public class FormField1 implements Serializable {
  private static final long serialVersionUID = 1L;
  private String nameOrNull;
  private String valueOrNull;
  public final boolean required;

  private FormField1(String value, boolean required) {
    this.valueOrNull = value;
    this.required = required;
  }

  public static FormField1 optionalField() {
    return optionalField(null);
  }

  public static FormField1 optionalField(String value) {
    return new FormField1(value, false);
  }

  public static FormField1 requiredField() {
    return requiredField(null);
  }

  public static FormField1 requiredField(String value) {
    return new FormField1(value, true);
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
