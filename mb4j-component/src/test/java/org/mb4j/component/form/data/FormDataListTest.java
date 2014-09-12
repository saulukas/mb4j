package org.mb4j.component.form.data;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class FormDataListTest {

    private static class Point extends FormData {

        FormField x = FormField.requiredField();
        FormField y = FormField.requiredField();

        Point(int x, int y) {
            this.x.value = Integer.toString(x);
            this.y.value = Integer.toString(y);
        }
    }

    private static class PointList extends FormDataList<Point> {

        PointList() {
            super(Point.class, asList(new Point(30, 31), new Point(40, 41), new Point(50, 51)));
        }
    }

    private static class Polygon extends FormData {

        PointList vertices;
    }

    @Test
    public void has_nice_toString() {
        System.out.println("Nice toString: " + new PointList());
        System.out.println("Nice toString: " + new Polygon());
    }

    @Test
    public void asFieldMap_indexes_items_starting_from_zero() {
        assertThat(new PointList().asFieldMap().keySet(),
                containsInAnyOrder(".0.x", ".0.y", ".2.y", ".2.x", ".1.y", ".1.x"));
    }
}
