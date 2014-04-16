package org.mb4j.brick.samples;

import org.mb4j.brick.Brick;
import org.mb4j.brick.template.TemplateType;

@TemplateType(".mustache")
public class MoreCompositeBrick extends Brick {
  final Brick composite1 = new CompositeBrick("Composite Hello 1");
  final Brick composite2 = new CompositeBrick("Composite Hello 2");
  final Brick simple = new SimpleBrick("Simple Hello");
  final String message;

  public MoreCompositeBrick() {
    this.message = "More Composite Hello";
  }
}
