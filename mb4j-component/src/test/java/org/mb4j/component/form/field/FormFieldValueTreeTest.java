package org.mb4j.component.form.field;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FormFieldValueTreeTest {
  private final FormFieldValueTree tree;

  public FormFieldValueTreeTest() {
    Map<String, String> name2value = new HashMap<>();
    name2value.put("address", "Some address");
    name2value.put("companyName", "Company Ltd.");
    name2value.put("country", "Rainland");
    name2value.put("embeddedInfo.detailA", "Some details for A");
    name2value.put("embeddedInfo.detailB", "Some details for B");
    name2value.put("employees.0.hobby", "fishing");
    name2value.put("employees.0.name", "Dima");
    name2value.put("employees.7.hobby", "dancing");
    name2value.put("employees.7.name", "Maria");
    name2value.put("employees.2.hobby", "walking");
    name2value.put("employees.2.name", "Jon");
    name2value.put("founder", "Tom Tomson");
    name2value.put("rating", "3");
    this.tree = FormFieldValueTree.fieldValueTreeOf(name2value);
  }

  @Test
  public void has_nice_toString() {
    System.out.println("\nNice toString: " + tree.toString("    ") + "\n");
  }

  @Test
  public void assigns_recursivelly_to_FormFields() {
    FormFieldRecordTest.ExtendedFields fields = new FormFieldRecordTest.ExtendedFields();
    fields.setValuesFrom(tree);
    System.out.println("\nFields after set vales: " + fields + "\n");
    assertEquals(fields.address.value, "Some address");
    assertEquals(fields.companyName.value, "Company Ltd.");
    assertEquals(fields.country.value, "Rainland");
    assertEquals(fields.embeddedInfo.detailA.value, "Some details for A");
    assertEquals(fields.embeddedInfo.detailB.value, "Some details for B");
    assertEquals(fields.employees.itemAt(0).hobby.value, "fishing");
    assertEquals(fields.employees.itemAt(0).name.value, "Dima");
    assertEquals(fields.employees.itemAt(1).hobby.value, "walking");
    assertEquals(fields.employees.itemAt(1).name.value, "Jon");
    assertEquals(fields.employees.itemAt(2).hobby.value, "dancing");
    assertEquals(fields.employees.itemAt(2).name.value, "Maria");
    assertEquals(fields.founder.value, "Tom Tomson");
    assertEquals(fields.rating.value, "3");
  }
}
