package org.mb4j.brick.samples.composition;

import org.mb4j.brick.Brick;
import org.mb4j.brick.samples.SimpleUnindentedBrick;

public class CompositeWithUnindentedBrick extends Brick {
  Brick composite = new CompositeBrick();
  Brick unindented = new SimpleUnindentedBrick();
}
