package org.mb4j.controller.form;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.controller.form.FormField.createOptionalField;
import static org.mb4j.controller.form.FormField.createRequiredField;

public class FormFieldGroupTest {
  private static class EmbeddedFields extends FormFieldGroup {
    FormField detailA = createOptionalField();
    FormField detailB = createOptionalField();
  }

  private static class PersonFields extends FormFieldGroup {
    FormField name = createRequiredField();
    FormField hobby = createOptionalField();

    PersonFields(String name, String hobby) {
      this.name.value = name;
      this.hobby.value = hobby;
    }
  }

  private static class BaseFields extends FormFieldGroup {
    FormField companyName = createOptionalField();
    FormField address = createOptionalField();
    FormField rating = createOptionalField();
    FormFieldList employees = new FormFieldList(asList(
        new PersonFields("Tom", "music"),
        new PersonFields("Ana", "dancing"),
        new PersonFields("Jonas", "walking")
    ));
  }

  private static class ExtendedFields extends BaseFields {
    final FormField founder = createOptionalField();
    final FormField country = createOptionalField();
    final EmbeddedFields embeddedInfo = new EmbeddedFields();
  }

  @Test
  public void asFieldMap_maps_field_names_to_FormField_includind_inherited_and_nested_ones() {
    FormFieldGroup fields = new ExtendedFields();
    assertThat(fields.asFieldMap().keySet(), containsInAnyOrder(
        "address",
        "companyName",
        "country",
        "embeddedInfo.detailA",
        "embeddedInfo.detailB",
        "employees.0.hobby",
        "employees.0.name",
        "employees.1.hobby",
        "employees.1.name",
        "employees.2.hobby",
        "employees.2.name",
        "founder",
        "rating"));
  }

  @Test
  public void has_nice_toStrnig() {
    System.out.println("nice toString: " + new ExtendedFields());
  }
}
