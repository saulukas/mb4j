package org.mb4j.samples;

import org.mb4j.Brick;
import org.mb4j.template.TemplateType;

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
