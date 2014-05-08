package org.mb4j.brick.samples.layout;

import org.mb4j.brick.Brick;
import org.mb4j.brick.EmptyBrick;
import org.mb4j.brick.template.TemplateType;

@TemplateType(".mustache")
public class LayoutBrick extends Brick {
  Brick header = new HeaderBrick();
  Brick footer = new FooterBrick();
  Brick content = new EmptyBrick();
}
