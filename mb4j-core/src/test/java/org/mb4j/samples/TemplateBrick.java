package org.mb4j.samples;

import org.mb4j.Brick;
import org.mb4j.bricks.EmptyBrick;
import org.mb4j.template.TemplateType;

@TemplateType(".mustache")
public class TemplateBrick extends Brick {
  Brick header = new TemplateHeaderBrick();
  Brick footer = new TemplateFooterBrick();
  Brick content = new EmptyBrick();
}
