package org.mb4j.component.page;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.view.ViewRequest;

public interface BrickBaker {
  MustacheBrick bakeBrickFrom(ViewRequest request);
}
