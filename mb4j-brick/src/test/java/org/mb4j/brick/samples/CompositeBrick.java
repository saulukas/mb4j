package org.mb4j.brick.samples;

import org.mb4j.brick.Brick;
import org.mb4j.brick.template.TemplateType;

@TemplateType(".mustache")
public class CompositeBrick extends Brick {
  final Brick simple1 = new SimpleBrick("First Hello");
  final Brick simple2 = new SimpleBrick("Second Hello");
  final String message;

  public CompositeBrick() {
    this("Composite Hello");
  }

  public CompositeBrick(String message) {
    this.message = message;
  }
}
