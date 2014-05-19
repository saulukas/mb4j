package org.mb4j.brick.samples.composition;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.brick.samples.SimpleBrick;
import org.mb4j.brick.template.TemplateType;

@TemplateType(".mustache")
public class CompositeBrick extends MustacheBrick {
  final MustacheBrick simple1 = new SimpleBrick("First Hello");
  final MustacheBrick simple2 = new SimpleBrick("Second Hello");
  final String message;

  public CompositeBrick() {
    this("Composite Hello");
  }

  public CompositeBrick(String message) {
    this.message = message;
  }
}
