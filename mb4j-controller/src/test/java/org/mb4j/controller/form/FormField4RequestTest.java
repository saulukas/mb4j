package org.mb4j.controller.form;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mb4j.controller.form.field.FormField;

public class FormField4RequestTest {
  @Test
  public void creates_map_of_attributes_of_FormField() {
    FormField field = FormField.createOptionalField("value123").withMaxSize(120);
    field.error = "Some easy to fix error.";
    FormField4Request field4request = new FormField4Request("name7", field);
    assertEquals(field4request.size(), 6);
    assertEquals(field4request.get("name"), "name7");
    assertEquals(field4request.get("value"), "value123");
    assertEquals(field4request.get("required"), false);
    assertEquals(field4request.get("hasError"), true);
    assertEquals(field4request.get("error"), "Some easy to fix error.");
    assertEquals(field4request.get("maxSize"), 120);
  }

  private static class FieldSubclass extends FormField {
    String customLabel = "Very nice label:";
    String color = "green";
  }

  @Test
  public void includes_attributes_from_subclass() {
    FieldSubclass field = new FieldSubclass();
    FormField4Request field4request = new FormField4Request("name7", field);
    assertEquals(field4request.get("customLabel"), "Very nice label:");
    assertEquals(field4request.get("color"), "green");
  }
}
