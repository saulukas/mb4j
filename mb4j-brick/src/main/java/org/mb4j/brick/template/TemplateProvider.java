package org.mb4j.brick.template;

import org.mb4j.brick.MustacheBrick;

public interface TemplateProvider {
  BrickTemplate templateFor(Class<? extends MustacheBrick> brickClass);
}
