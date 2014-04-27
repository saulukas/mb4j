package org.mb4j.controller.mapping;

import org.mb4j.controller.form.Form;

public interface FormClass2NameResolver {
  String formNameOf(Class<? extends Form> formClass);
}
