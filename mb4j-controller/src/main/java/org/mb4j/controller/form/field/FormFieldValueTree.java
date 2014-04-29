package org.mb4j.controller.form.field;

import com.google.common.base.Splitter;
import java.util.Map;

public class FormFieldValueTree {
  public final FormFieldValueNode root = null;
  public final Map<String, FormFieldValueTree> children = null;

  public static FormFieldValueTree buildTreeFrom(Map<String, String> name2value) {
    FormFieldValueNode root = new FormFieldValueNode();
    for (Map.Entry<String, String> entry : name2value.entrySet()) {
      String name = entry.getKey();
      String value = entry.getValue();
      FormFieldValueNode node = root;
      for (String nameSegment : Splitter.on(".").split(name)) {
        node = node.findOrCreateChildFor(nameSegment);
      }
      node.value = value;
    }
    System.out.println("Rootas: " + root);
    return new FormFieldValueTree();
  }
}
