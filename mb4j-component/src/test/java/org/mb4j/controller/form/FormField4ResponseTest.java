package org.mb4j.controller.form;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mb4j.controller.form.field.FormField;

public class FormField4ResponseTest {
  @Test
  public void creates_map_of_attributes_of_FormField() {
    FormField field = FormField.createOptionalField("value123").withMaxSize(120);
    field.enabled = false;
    field.error = "Some easy to fix error.";
    FormField4Response field4Response = new FormField4Response("name7", field);
    assertEquals(field4Response.size(), 8);
    assertEquals(field4Response.get("name"), "name7");
    assertEquals(field4Response.get("value"), "value123");
    assertEquals(field4Response.get("required"), false);
    assertEquals(field4Response.get("enabled"), false);
    assertEquals(field4Response.get("visible"), true);
    assertEquals(field4Response.get("hasError"), true);
    assertEquals(field4Response.get("error"), "Some easy to fix error.");
    assertEquals(field4Response.get("maxSize"), 120);
  }

  private static class FieldSubclass extends FormField {
    String customLabel = "Very nice label:";
    String color = "green";
  }

  @Test
  public void includes_attributes_from_subclass() {
    FieldSubclass field = new FieldSubclass();
    FormField4Response field4Response = new FormField4Response("name7", field);
    assertEquals(field4Response.get("customLabel"), "Very nice label:");
    assertEquals(field4Response.get("color"), "green");
  }
}
