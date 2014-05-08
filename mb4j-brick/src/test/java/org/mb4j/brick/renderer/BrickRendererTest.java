package org.mb4j.brick.renderer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mb4j.brick.Brick;
import org.mb4j.brick.TestingUtils;
import org.mb4j.brick.samples.composition.CompositeBrick;
import org.mb4j.brick.samples.composition.CompositeWithUnindentedBrick;
import org.mb4j.brick.samples.lists.ListOfBrickItems;
import org.mb4j.brick.samples.lists.ListOfCustomItems;
import org.mb4j.brick.samples.composition.MoreCompositeBrick;
import org.mb4j.brick.samples.recursion.RecursiveBrick;
import org.mb4j.brick.samples.SimpleBrick;
import org.mb4j.brick.samples.SimpleUnindentedBrick;
import org.mb4j.brick.samples.layout.LayoutBrick;
import org.mb4j.brick.samples.layout.LayoutExtensionBrick;
import org.mb4j.brick.samples.layout.LayoutExtensionExtensionBrick;
import org.mb4j.brick.samples.Utf8Brick;
import org.mb4j.brick.samples.composition.VeryCompositeBrick;

public class BrickRendererTest {
  @Test
  public void renders_simple_brick() {
    assertRendererOutputFor(new SimpleBrick());
  }

  @Test
  public void renders_composite_brick() {
    assertRendererOutputFor(new CompositeBrick());
  }

  @Test
  public void renders_more_composite_brick() {
    assertRendererOutputFor(new MoreCompositeBrick());
  }

  @Test
  public void renders_very_composite_brick() {
    VeryCompositeBrick.Baker baker = new VeryCompositeBrick.Baker();
    String message = "Very Composite Hello";
    assertRendererOutputFor(baker.bakeBrick(new VeryCompositeBrick.Baker.Params(message)));
  }

  @Test
  public void renders_recursive_brick() {
    assertRendererOutputFor(
        new RecursiveBrick("Simas",
            new RecursiveBrick("Jonas",
                new RecursiveBrick("Antanas",
                    new RecursiveBrick("Ona")))));
  }

  @Test
  public void renders_list_of_custom_items() {
    assertRendererOutputFor(new ListOfCustomItems());
  }

  @Test
  public void renders_list_of_bricks() {
    assertRendererOutputFor(new ListOfBrickItems());
  }

  @Test
  public void renders_template_brick() {
    assertRendererOutputFor(new LayoutBrick());
  }

  @Test
  public void renders_template_extension_brick() {
    assertRendererOutputFor(new LayoutExtensionBrick());
  }

  @Test
  public void renders_template_extension_extension_brick() {
    assertRendererOutputFor(new LayoutExtensionExtensionBrick());
  }

  @Test
  public void renders_utf8_brick() {
    assertRendererOutputFor(new Utf8Brick());
  }

  @Test
  public void renders_simple_unindented_brick() {
    assertRendererOutputFor(new SimpleUnindentedBrick());
  }

  @Test
  public void renders_composite_with_unindented_brick() {
    assertRendererOutputFor(new CompositeWithUnindentedBrick());
  }

  private void assertRendererOutputFor(Brick brick) {
    String actual = RendererUtils.renderToString4Development(brick);
    String expected = TestingUtils.fileContentFor(brick.getClass(), ".expected");
    assertThat(actual, is(expected));
  }
}
