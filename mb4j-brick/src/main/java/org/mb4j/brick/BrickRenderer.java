package org.mb4j.brick;

import org.mb4j.brick.renderer.RenderingScope;
import org.mb4j.brick.template.TemplateProvider;
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
