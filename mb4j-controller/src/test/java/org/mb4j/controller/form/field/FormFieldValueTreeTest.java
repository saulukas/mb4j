package org.mb4j.controller.form.field;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class FormFieldValueTreeTest {
  public FormFieldValueTreeTest() {
  }

  @Test
  public void has_nice_toString() {
    Map<String, String> name2value = new HashMap<>();
    name2value.put("address", "Some address");
    name2value.put("companyName", "Company Ltd.");
    name2value.put("country", "Rainland");
    name2value.put("embeddedInfo.detailA", "Some details for A");
    name2value.put("embeddedInfo.detailB", "Some details for B");
    name2value.put("employees.0.hobby", "fishing");
    name2value.put("employees.0.name", "Dima");
    name2value.put("employees.1.hobby", "dancing");
    name2value.put("employees.1.name", "Ana");
    name2value.put("employees.2.hobby", "walking");
    name2value.put("employees.2.name", "Jon");
    name2value.put("founder", "Tom Tomson");
    name2value.put("rating", "3");
    FormFieldValueTree tree = FormFieldValueTree.buildTreeFrom(name2value);
    System.out.println("\nNice toString: " + tree.toString("    ") + "\n");
  }
}
