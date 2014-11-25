package org.mb4j.brick;

import org.mb4j.brick.compiler.TemplatePart;
import org.mb4j.brick.renderer.RendererOutput;

public class Template<T> {

    private final TemplatePart part;

    Template(TemplatePart part) {
        this.part = part;
    }

    void render(RendererOutput out, T brick) {
        part.render(out, brick);
    }
}
