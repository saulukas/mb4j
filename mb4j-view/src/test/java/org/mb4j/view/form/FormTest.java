package org.mb4j.view.form;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.view.ViewTesting.actionParamNameResolver4Tests;
import static org.mb4j.view.form.FormField.optionalField;
import static org.mb4j.view.form.FormField.requiredField;

public class FormTest {
  static class Form4Testing extends Form {
    FormField id = requiredField("1000");
    FormField country = requiredField("Lithuania");
    FormField city = optionalField("Vilnius");
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
