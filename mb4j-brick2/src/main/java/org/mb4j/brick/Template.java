package org.mb4j.brick;

import org.mb4j.brick.compiler.TemplatePart;
import org.mb4j.brick.renderer.RendererOutput;

public class Template<T> {

    private final TemplatePart root;

    Template(TemplatePart root) {
        this.root = root;
    }

    void render(RendererOutput out, T context) {
        root.render(out, context);
    }
}
