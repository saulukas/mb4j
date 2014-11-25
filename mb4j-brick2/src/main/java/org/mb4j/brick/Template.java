package org.mb4j.brick;

import org.mb4j.brick.internal.TemplatePart;
import org.mb4j.brick.internal.TemplateWriter;

public class Template<T> {

    private final TemplatePart part;

    Template(TemplatePart part) {
        this.part = part;
    }

    void render(TemplateWriter out, T brick) {
        part.render(out, brick);
    }
}
