package org.mb4j.controller.form.field;

import com.google.common.base.Splitter;
import java.util.Map;
import org.mb4j.controller.utils.SimpleClassName;

public class FormFieldValueTree {
  public final FormFieldValueNode root;

  private FormFieldValueTree(FormFieldValueNode root) {
    this.root = root;
  }

  public static FormFieldValueTree fieldValueTreeOf(Map<String, String> name2value) {
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
    return new FormFieldValueTree(root);
  }

  @Override
  public String toString() {
    return toString("");
  }

  public String toString(String margin) {
    return SimpleClassName.of(getClass()) + root.toString(margin);
  }
}
