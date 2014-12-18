package org.mb4j.brick.compiler;

import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.brick.compiler.MBCompiler.compile;

public class MBCompilerTest {

    private static class Brick {

        String name = "Jonas";
        int nr = 123;

        static String TEMPLATE = "Hello {{name}}! You number is {{nr}}.";
    }

    @Test
    public void compiles_some_basic_brick_which_renders_as_expected() {
        MBTemplate<Brick> template = compile(Brick.class, Brick.TEMPLATE);
        assertThat(template.render(new Brick()), is("Hello Jonas! You number is 123."));
    }

    @Test
    public void compiles_text_and_variables() {
        List<Segment> segments = segmentsOf(compile(Brick.class, "a {{name}} {{nr}} c"));
        assertThat(segments.size(), is(5));
        assertThat(valueOfTextSegment(segments.get(0)), is("a "));
        assertThat(nameOfFieldSegment(segments.get(1)), is("name"));
        assertThat(valueOfTextSegment(segments.get(2)), is(" "));
        assertThat(nameOfFieldSegment(segments.get(3)), is("nr"));
        assertThat(valueOfTextSegment(segments.get(4)), is(" c"));
    }

    @Test
    public void no_extra_segments_beweent_adjacent_tags() {
        List<Segment> segments = segmentsOf(compile(Brick.class, "{{name}}{{nr}}"));
        assertThat(segments.size(), is(4));
        assertThat(nameOfFieldSegment(segments.get(0)), is("name"));
        assertThat(nameOfFieldSegment(segments.get(1)), is("nr"));
    }

    private static String nameOfFieldSegment(Segment segment) {
        return ((SegmentField) segment).field.getName();
    }

    private static String valueOfTextSegment(Segment segment) {
        return ((SegmentText) segment).text;
    }

    private static List<Segment> segmentsOf(MBTemplate template) {
        return ((SegmentContext) template.root).segments;
    }

}
