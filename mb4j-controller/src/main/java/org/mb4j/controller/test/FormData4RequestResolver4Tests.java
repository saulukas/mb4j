package org.mb4j.controller.test;

import org.mb4j.brick.Brick;
import org.mb4j.brick.RawBrick;
import org.mb4j.controller.form.FormData4RequestResolver;

public class FormData4RequestResolver4Tests extends FormData4RequestResolver {
  public static final FormData4RequestResolver4Tests INSTANCE = new FormData4RequestResolver4Tests();

  public FormData4RequestResolver4Tests() {
    super(FormClass2NameResolver4Tests.INSTANCE);
  }

  @Override
  protected Brick createHeaderBrick(String formName) {
    return new RawBrick("<p>Some form header for testing of form " + formName + "</p>");
  }
}
