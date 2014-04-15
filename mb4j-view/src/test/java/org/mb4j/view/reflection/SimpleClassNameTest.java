package org.mb4j.view.reflection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mb4j.view.reflection.SimpleClassNameTest.Class_1.Class_1_1;
import org.mb4j.view.reflection.SimpleClassNameTest.Class_1.Class_1_2;
import org.mb4j.view.reflection.SimpleClassNameTest.Class_1.Class_1_2.Class_1_2_3;

public class SimpleClassNameTest {
  static class Class_1 {
    static class Class_1_1 {
    }

    static class Class_1_2 {
      static class Class_1_2_3 {
      }
    }
  }

  @Test
  public void joins_simle_names_of_all_enclosing_classes() {
    String enclosing = getClass().getSimpleName() + ".";
    assertThat(SimpleClassName.of(Class_1.class), is(enclosing + "Class_1"));
    assertThat(SimpleClassName.of(Class_1_1.class), is(enclosing + "Class_1.Class_1_1"));
    assertThat(SimpleClassName.of(Class_1_2.class), is(enclosing + "Class_1.Class_1_2"));
    assertThat(SimpleClassName.of(Class_1_2_3.class), is(enclosing + "Class_1.Class_1_2.Class_1_2_3"));
  }
}
