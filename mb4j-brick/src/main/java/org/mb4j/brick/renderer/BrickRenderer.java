package org.mb4j.brick.renderer;

import java.io.Writer;
import org.mb4j.brick.Brick;
import org.mb4j.brick.template.TemplateProvider;

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
