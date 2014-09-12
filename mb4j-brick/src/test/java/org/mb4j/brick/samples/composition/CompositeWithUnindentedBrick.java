package org.mb4j.brick.samples.composition;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.brick.samples.SimpleUnindentedBrick;

public class CompositeWithUnindentedBrick extends MustacheBrick {

    MustacheBrick composite = new CompositeBrick();
    MustacheBrick unindented = new SimpleUnindentedBrick();
}
