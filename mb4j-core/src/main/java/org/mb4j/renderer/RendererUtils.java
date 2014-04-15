package org.mb4j.renderer;

import java.io.StringWriter;
import org.mb4j.Brick;
import org.mb4j.BrickRenderer;
import org.mb4j.template.TemplateProviderFromClasspath;

public class RendererUtils {
  public static String renderToString4Development(Brick brick) {
    return renderToString(brick, renderer4Development());
  }

  public static BrickRenderer renderer4Development() {
    return new BrickRenderer(new TemplateProviderFromClasspath());
  }

  public static String renderToString(Brick brick, BrickRenderer renderer) {
    StringWriter out = new StringWriter();
    renderer.render(brick, out);
    return out.toString();
  }
}
