package org.mb4j.controller.test;

import org.mb4j.controller.form.Form;
import org.mb4j.controller.mapping.FormClass2NameResolver;
import org.mb4j.controller.utils.SimpleClassName;

public class FormClass2NameResolver4Tests implements FormClass2NameResolver {
  public static final FormClass2NameResolver4Tests INSTANCE = new FormClass2NameResolver4Tests();

  @Override
  public String formNameOf(Class<? extends Form> formClass) {
    return SimpleClassName.of(formClass);
  }
}
