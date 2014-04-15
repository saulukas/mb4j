package org.mb4j.samples;

import org.mb4j.Brick;
import org.mb4j.template.TemplateType;

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
