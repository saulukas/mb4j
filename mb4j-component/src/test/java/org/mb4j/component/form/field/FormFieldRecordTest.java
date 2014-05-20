package org.mb4j.component.form.field;

import org.mb4j.component.form.field.FormField;
import org.mb4j.component.form.field.FormFieldRecord;
import org.mb4j.component.form.field.FormFieldList;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.mb4j.component.form.field.FormField.createOptionalField;
import static org.mb4j.component.form.field.FormField.createRequiredField;

public class FormFieldRecordTest {
  static class EmbeddedFields extends FormFieldRecord {
    FormField detailA = createOptionalField("Some details about something.");
    FormField detailB = createOptionalField();
  }

  static class PersonFields extends FormFieldRecord {
    FormField name = createRequiredField().withMaxSize(75);
    FormField hobby = createOptionalField();

    PersonFields() {
    }

    PersonFields(String name, String hobby) {
      this.name.value = name;
      this.hobby.value = hobby;
    }
  }

  static class BaseFields extends FormFieldRecord {
    FormField companyName = createRequiredField("Bricks Ltd.");
    FormField address = createOptionalField("River Street 25");
    FormField rating = createOptionalField("99");
    FormFieldList<PersonFields> employees = FormFieldList.of(PersonFields.class,
        new PersonFields("Tom", "music"),
        new PersonFields("Ana", "dancing"),
        new PersonFields("Jonas", "walking")
    );
  }

  static class ExtendedFields extends BaseFields {
    FormField founder = createOptionalField("Jon Jonson");
    FormField country = createOptionalField("Rainland");
    EmbeddedFields embeddedInfo = new EmbeddedFields();
  }

  @Test
  public void asFieldMap_maps_field_names_to_FormField_including_inherited_and_nested_ones() {
    FormFieldRecord fields = new ExtendedFields();
    Map<String, FormField> fieldMap = fields.asFieldMap();
    assertEquals(fieldMap.size(), 13);
    assertEquals(fieldMap.get("address").value, "River Street 25");
    assertEquals(fieldMap.get("companyName").value, "Bricks Ltd.");
    assertEquals(fieldMap.get("country").value, "Rainland");
    assertEquals(fieldMap.get("embeddedInfo.detailA").value, "Some details about something.");
    assertEquals(fieldMap.get("embeddedInfo.detailB").value, "");
    assertEquals(fieldMap.get("employees.0.hobby").value, "music");
    assertEquals(fieldMap.get("employees.0.name").value, "Tom");
    assertEquals(fieldMap.get("employees.1.hobby").value, "dancing");
    assertEquals(fieldMap.get("employees.1.name").value, "Ana");
    assertEquals(fieldMap.get("employees.2.hobby").value, "walking");
    assertEquals(fieldMap.get("employees.2.name").value, "Jonas");
    assertEquals(fieldMap.get("founder").value, "Jon Jonson");
    assertEquals(fieldMap.get("rating").value, "99");
  }

  @Test
  public void has_nice_toString() {
    System.out.println("nice toString: " + new ExtendedFields());
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
