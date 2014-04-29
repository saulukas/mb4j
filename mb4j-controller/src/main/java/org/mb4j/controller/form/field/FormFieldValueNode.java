package org.mb4j.controller.form.field;

import static com.google.common.collect.Lists.newArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FormFieldValueNode {
  String value = null;
  Map<String, FormFieldValueNode> children = null;

  FormFieldValueNode findOrCreateChildFor(String nameSegment) {
    if (children == null) {
      children = new HashMap<>();
    }
    FormFieldValueNode child = children.get(nameSegment);
    if (child == null) {
      child = new FormFieldValueNode();
      children.put(nameSegment, child);
    }
    return child;
  }

  @Override
  public String toString() {
    return toString("");
  }

  public String toString(String margin) {
    String result = (value == null ? "" : " = [" + value + "]");
    if (children != null) {
      List<String> childNames = newArrayList(children.keySet());
      Collections.sort(childNames);
      Iterator<String> namesIterator = childNames.iterator();
      while (namesIterator.hasNext()) {
        String childName = namesIterator.next();
        FormFieldValueNode child = children.get(childName);
        //result += "\n" + margin + "|";
        result += "\n" + margin + "+-- " + childName
            + child.toString(margin + (namesIterator.hasNext() ? "|   " : "    "));
      }
    }
    return result;
  }
}
