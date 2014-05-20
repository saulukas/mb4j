package org.mb4j.component.form.field;

import org.mb4j.component.form.field.FormField;
import org.mb4j.component.form.field.FormFieldRecord;
import org.mb4j.component.form.field.FormFieldList;
import static java.util.Arrays.asList;
import java.util.List;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;

public class FormFieldListTest {
  private static class Point extends FormFieldRecord {
    FormField x = FormField.createRequiredField();
    FormField y = FormField.createRequiredField();

    Point(int x, int y) {
      this.x.value = Integer.toString(x);
      this.y.value = Integer.toString(y);
    }
  }

  private static class PointList extends FormFieldList<Point> {
    PointList(List<Point> points) {
      super(Point.class, points);
    }
  }

  private static class PointMatrix extends FormFieldRecord {
    FormFieldList<PointList> matrix = FormFieldList.of(PointList.class,
        new PointList(asList(new Point(0, 0), new Point(0, 1), new Point(0, 2))),
        new PointList(asList(new Point(1, 0), new Point(1, 1), new Point(1, 2))),
        new PointList(asList(new Point(2, 0), new Point(2, 1), new Point(2, 2)))
    );
  }

  @Test
  public void has_nice_toString() {
    System.out.println("Nice toString: " + new PointMatrix());
  }

  @Test
  public void asFieldMap_includes_nested_lists() {
    PointMatrix fields = new PointMatrix();
    assertThat(fields.asFieldMap().keySet(), containsInAnyOrder(
        "matrix.0.0.x",
        "matrix.0.0.y",
        "matrix.0.1.x",
        "matrix.0.1.y",
        "matrix.0.2.x",
        "matrix.0.2.y",
        "matrix.1.0.x",
        "matrix.1.0.y",
        "matrix.1.1.x",
        "matrix.1.1.y",
        "matrix.1.2.x",
        "matrix.1.2.y",
        "matrix.2.0.x",
        "matrix.2.0.y",
        "matrix.2.1.x",
        "matrix.2.1.y",
        "matrix.2.2.x",
        "matrix.2.2.y"
    ));
  }

  @Test
  public void does_not_allow_FormField_as_item_type() {
    try {
      FormFieldList.of(FormField.class);
      fail();
    } catch (RuntimeException ex) {
      System.out.println("Expected exception: " + ex);
    }
  }
}
