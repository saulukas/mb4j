package org.mb4j.brick.samples;

import org.mb4j.brick.Brick;
import org.mb4j.brick.template.TemplateType;

@TemplateType(".mustache")
public class SimpleBrick extends Brick {
  final String message;

  public SimpleBrick() {
    this("Hello");
  }

  public SimpleBrick(String message) {
    this.message = message;
  }
}
