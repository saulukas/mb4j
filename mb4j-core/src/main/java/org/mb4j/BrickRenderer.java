package org.mb4j;

import org.mb4j.renderer.RenderingScope;
import org.mb4j.template.TemplateProvider;
import java.io.Writer;

public class BrickRenderer {
  private final TemplateProvider templateProvider;

  public BrickRenderer(TemplateProvider templateProvider) {
    this.templateProvider = templateProvider;
  }

  public void render(Brick brick, Writer out) {
    RenderingScope scope = new RenderingScope(out, templateProvider);
    scope.render(brick);
  }
}
