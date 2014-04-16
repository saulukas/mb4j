package org.mb4j.brick.samples;

import org.mb4j.brick.Brick;

public class CompositeWithUnindentedBrick extends Brick {
  Brick composite = new CompositeBrick();
  Brick unindented = new SimpleUnindentedBrick();
}
