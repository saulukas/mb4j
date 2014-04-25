package org.mb4j.controller.form1;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.controller.form1.FormField1.optionalField;
import static org.mb4j.controller.form1.FormField1.requiredField;
import static org.mb4j.controller.test.ControllerTesting.actionParamNameResolver4Tests;

public class FormTest {
  static class Form4Testing extends Form1 {
    FormField1 id = requiredField("1000");
    FormField1 country = requiredField("Lithuania");
    FormField1 city = optionalField("Vilnius");
  }

  @Test
  public void resolves_param_names_attribute_to_their_attribute_name_in_containing_class() {
    Form4Testing form = new Form4Testing();
    form.resolveFieldNames(actionParamNameResolver4Tests());
    assertThat(form.id.name(), is("id"));
    assertThat(form.country.name(), is("country"));
    assertThat(form.city.name(), is("city"));
  }

  @Test
  public void has_nice_toString() {
    Form4Testing form = new Form4Testing();
    form.resolveFieldNames(actionParamNameResolver4Tests());
    System.out.println(form);
  }
}
