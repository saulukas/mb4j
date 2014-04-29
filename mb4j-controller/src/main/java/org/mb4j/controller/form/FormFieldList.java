package org.mb4j.controller.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class FormFieldList extends FormFieldBase {
  public final List<FormFieldBase> list = new ArrayList<>();

  public FormFieldList() {
  }

  public FormFieldList(Collection<? extends FormFieldBase> items) {
    list.addAll(items);
  }

  @Override
  void collectFields(String nameInParent, Map<String, FormField> fieldMap) {
    int index = 0;
    for (FormFieldBase member : list) {
      member.collectFields(nameInParent + "." + index, fieldMap);
      index += 1;
    }
  }

  @Override
  public String toString(String margin) {
    String result = getClass().getSimpleName() + ": " + list.size();
    int index = 0;
    for (FormFieldBase group : list) {
      result += "\n" + margin + "    " + index + ". " + group.toString(margin + "    ");
      index += 1;
    }
    return result;
  }
}
