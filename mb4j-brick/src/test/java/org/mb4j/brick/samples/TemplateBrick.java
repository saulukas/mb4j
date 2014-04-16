package org.mb4j.brick.samples;

import org.mb4j.brick.Brick;
import org.mb4j.brick.EmptyBrick;
import org.mb4j.brick.template.TemplateType;

@TemplateType(".mustache")
public class TemplateBrick extends Brick {
  Brick header = new TemplateHeaderBrick();
  Brick footer = new TemplateFooterBrick();
  Brick content = new EmptyBrick();
}
