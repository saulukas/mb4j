package org.mb4j.controller.form;

import org.mb4j.controller.form.FormField;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.mb4j.controller.form.FormField.optionalField;

public class FormFieldTest {
  @Test
  public void param_name_must_be_resolved_before_using_it() {
    FormField param = optionalField("abc");
    try {
      param.name();
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Nice error message: " + ex);
    }
    param.resolveNameTo("someName");
    assertThat(param.name(), is("someName"));
  }
}
