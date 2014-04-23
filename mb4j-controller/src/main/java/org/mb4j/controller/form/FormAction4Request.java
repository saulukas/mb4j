package org.mb4j.controller.form;

public class FormAction4Request {
  public final String name;

  public FormAction4Request(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    throw new UnsupportedOperationException(getClass().getSimpleName() + ".toString() is not supported."
        + " Access attribute 'name' instead. Name4Request='" + name + "'");
  }
}
