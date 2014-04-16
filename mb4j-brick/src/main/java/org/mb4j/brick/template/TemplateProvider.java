package org.mb4j.brick.template;

import org.mb4j.brick.Brick;

public interface TemplateProvider {
  BrickTemplate templateFor(Class<? extends Brick> brickClass);
}
