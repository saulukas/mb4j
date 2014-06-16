package org.mb4j.component.form.data;

import org.mb4j.component.form.data.binding.FormFieldValueNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.mb4j.component.utils.ReflectionUtils;

public class FormDataList<T extends FormData> extends AbstractFormData {
  public final Class<T> itemClass;
  private List<T> list;

  protected FormDataList(Class<T> itemClass) {
    this(itemClass, new ArrayList<T>());
  }

  protected FormDataList(Class<T> itemClass, List<T> list) {
    this.itemClass = itemClass;
    this.list = new ArrayList<>(list);
  }

  public static <T extends FormData> FormDataList<T> of(Class<T> itemClass, T... items) {
    return new FormDataList<>(itemClass, Arrays.asList(items));
  }

  public static <T extends FormData> FormDataList<T> of(Class<T> itemClass, List<T> items) {
    return new FormDataList<>(itemClass, items);
  }

  public T itemAt(int index) {
    return list.get(index);
  }

  @Override
  public boolean hasErrors() {
    for (AbstractFormData member : list) {
      if (member.hasErrors()) {
        return true;
      }
    }
    return false;
  }

  @Override
  void collectFields(String nameInParent, Map<String, FormField> fieldMap) {
    int index = 0;
    for (AbstractFormData member : list) {
      member.collectFields(nameInParent + "." + index, fieldMap);
      index += 1;
    }
  }

  @Override
  void setValuesFrom(FormFieldValueNode node) {
    Map<Integer, T> newValues = new TreeMap<>(); // need ordering by key
    for (Map.Entry<String, FormFieldValueNode> entry : node.children.entrySet()) {
      String name = entry.getKey();
      int index = -1;
      try {
        index = Integer.parseInt(name);
      } catch (RuntimeException ex) {
      }
      if (index >= 0) {
        T item = findOrCreateItemAt(index, newValues);
        item.setValuesFrom(entry.getValue());
      }
    }
    list = new ArrayList<>(newValues.values());
  }

  private T findOrCreateItemAt(int index, Map<Integer, T> values) {
    T value = values.get(index);
    if (value == null) {
      value = ReflectionUtils.createInstanceOf(itemClass);
      values.put(index, value);
    }
    return value;
  }

  @Override
  public String toString(String margin) {
    String result = getClass().getSimpleName() + ": " + list.size();
    int index = 0;
    for (AbstractFormData group : list) {
      result += "\n" + margin + "    " + index + ". " + group.toString(margin + "    ");
      index += 1;
    }
    return result;
  }
}
