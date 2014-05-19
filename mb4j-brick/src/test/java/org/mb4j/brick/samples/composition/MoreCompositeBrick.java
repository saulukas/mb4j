package org.mb4j.brick.samples.composition;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.brick.samples.SimpleBrick;
import org.mb4j.brick.template.TemplateType;

@TemplateType(".mustache")
public class MoreCompositeBrick extends MustacheBrick {
  final MustacheBrick composite1 = new CompositeBrick("Composite Hello 1");
  final MustacheBrick composite2 = new CompositeBrick("Composite Hello 2");
  final MustacheBrick simple = new SimpleBrick("Simple Hello");
  final String message;

  public MoreCompositeBrick() {
    this.message = "More Composite Hello";
  }
}
