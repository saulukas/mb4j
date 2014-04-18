package org.mb4j.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AAA_Concepts {
  void test1() {
    try {
      List<Class<? extends View>> controllers = new ArrayList<>();
      Set<Class<?>> visitedClasses = new HashSet<>();
      Class viewKlass = View.class;
      collectControllersFor(viewKlass, false, visitedClasses, controllers);
      System.out.println("Controolers for " + viewKlass.getSimpleName() + ": " + controllers);
    } catch (Exception ex) {
      System.out.println("Bedele: " + ex);
    }
  }

  private void collectControllersFor(
      Class<?> klass,
      boolean considerThisKlass,
      Set<Class<?>> visitedClasses,
      List<Class<? extends View>> result) throws Exception {
    if (visitedClasses.contains(klass)) {
      return;
    }
    visitedClasses.add(klass);
    if (considerThisKlass && View.class.isAssignableFrom(klass)) {
      result.add((Class<? extends View>) klass);
    }
    for (Field declaredField : klass.getDeclaredFields()) {
      collectControllersFor(declaredField.getType(), true, visitedClasses, result);
    }
    if (klass.getSuperclass() != null) {
      collectControllersFor(klass.getSuperclass(), false, visitedClasses, result);
    }
  }
}
