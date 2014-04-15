package org.mb4j.template;

import org.mb4j.Brick;

public interface TemplateProvider {
  BrickTemplate templateFor(Class<? extends Brick> brickClass);
}
