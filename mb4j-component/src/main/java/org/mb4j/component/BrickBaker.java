package org.mb4j.component;

import org.mb4j.brick.MustacheBrick;

public interface BrickBaker {

    MustacheBrick bakeBrick(Request request);
}
