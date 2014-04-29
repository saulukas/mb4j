package org.mb4j.controller.form;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.mb4j.controller.form.FormField.createOptionalField;
import static org.mb4j.controller.form.FormField.createRequiredField;

public class FormFieldRecordTest {
  private static class EmbeddedFields extends FormFieldRecord {
    FormField detailA = createOptionalField("Some details about something.");
    FormField detailB = createOptionalField();
  }

  private static class PersonFields extends FormFieldRecord {
    FormField name = createRequiredField().withMaxSize(75);
    FormField hobby = createOptionalField();

    PersonFields(String name, String hobby) {
      this.name.value = name;
      this.hobby.value = hobby;
    }
  }

  private static class BaseFields extends FormFieldRecord {
    FormField companyName = createRequiredField("Bricks Ltd.");
    FormField address = createOptionalField("River Street 25");
    FormField rating = createOptionalField("99").withErrorMessage("Rating must be between 0 and 5.");
    FormFieldList<PersonFields> employees = FormFieldList.of(PersonFields.class,
        new PersonFields("Tom", "music"),
        new PersonFields("Ana", "dancing"),
        new PersonFields("Jonas", "walking")
    );
  }

  private static class ExtendedFields extends BaseFields {
    FormField founder = createOptionalField("Jon Jonson");
    FormField country = createOptionalField("Rainland");
    EmbeddedFields embeddedInfo = new EmbeddedFields();
  }

  @Test
  public void has_nice_toString() {
    System.out.println("nice toString: " + new ExtendedFields());
  }

  @Test
  public void asFieldMap_maps_field_names_to_FormField_including_inherited_and_nested_ones() {
    FormFieldRecord fields = new ExtendedFields();
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
        "rating"
    ));
  }

  private static class NonFieldAttributes extends FormFieldRecord {
    FormField validField = createOptionalField();
    Number invalidField;
  }

  @Test
  public void asFieldMap_fails_if_non_FormFieldBase_attributes_are_found() {
    try {
      new NonFieldAttributes().asFieldMap();
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Expected exception: " + ex);
    }
  }
}
