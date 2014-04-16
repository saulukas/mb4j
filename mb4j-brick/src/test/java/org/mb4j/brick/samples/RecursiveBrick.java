package org.mb4j.brick.samples;

import org.mb4j.brick.Brick;
import org.mb4j.brick.template.TemplateType;

@TemplateType(".mustache")
public class RecursiveBrick extends Brick {
  final RecursiveBrick next;
  final String message;

  public RecursiveBrick(String message) {
    this(message, null);
  }

  public RecursiveBrick(String message, RecursiveBrick next) {
    this.message = message;
    this.next = next;
  }

  boolean hasNext() {
    return next != null;
  }
}
