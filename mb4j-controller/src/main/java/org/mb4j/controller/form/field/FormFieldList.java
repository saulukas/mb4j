package org.mb4j.controller.form.field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.mb4j.controller.utils.SimpleClassName;

public class FormFieldList<T extends FormFieldBase> extends FormFieldBase {
  public final Class<T> itemClass;
  private final List<T> list;

  protected FormFieldList(Class<T> itemClass) {
    this(itemClass, new ArrayList<T>());
  }

  protected FormFieldList(Class<T> itemClass, List<T> list) {
    if (!FormFieldRecord.class.isAssignableFrom(itemClass)
        && !FormFieldList.class.isAssignableFrom(itemClass)) {
      throw new RuntimeException("Items of " + SimpleClassName.of(FormFieldList.class)
          + " may only be of class " + SimpleClassName.of(FormFieldRecord.class)
          + " or " + SimpleClassName.of(FormFieldList.class) + " but found: " + itemClass);
    }
    this.itemClass = itemClass;
    this.list = list;
  }

  public static <T extends FormFieldBase> FormFieldList<T> of(Class<T> itemClass, T... items) {
    return new FormFieldList<>(itemClass, Arrays.asList(items));
  }

  public static <T extends FormFieldBase> FormFieldList<T> of(Class<T> itemClass, List<T> items) {
    return new FormFieldList<>(itemClass, items);
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
