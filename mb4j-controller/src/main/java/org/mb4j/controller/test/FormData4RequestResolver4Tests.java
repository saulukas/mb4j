package org.mb4j.controller.test;

import org.mb4j.brick.Brick;
import org.mb4j.brick.RawBrick;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormData4RequestResolver;
import org.mb4j.controller.mapping.FormClass2NameResolver;

public class FormData4RequestResolver4Tests extends FormData4RequestResolver {
  public static final FormData4RequestResolver4Tests INSTANCE = new FormData4RequestResolver4Tests();

  @Override
  protected Brick createHeaderBrick(FormClass2NameResolver formResolver, FormData<?> formData) {
    return new RawBrick("<p>Some form header for testing...</p>");
  }
}
