package org.mb4j.samples;

import org.mb4j.Brick;

public class CompositeWithUnindentedBrick extends Brick {
  Brick composite = new CompositeBrick();
  Brick unindented = new SimpleUnindentedBrick();
}
