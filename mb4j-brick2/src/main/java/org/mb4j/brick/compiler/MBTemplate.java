package org.mb4j.brick.compiler;

import org.mb4j.brick.renderer.RendererOutput;

public class MBTemplate<T> {

    private final Segment root;

    MBTemplate(Segment root) {
        this.root = root;
    }

    public void render(RendererOutput out, T context) {
        root.render(out, context);
    }
}
