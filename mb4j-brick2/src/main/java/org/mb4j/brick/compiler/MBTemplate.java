package org.mb4j.brick.compiler;

import java.io.StringWriter;
import org.mb4j.brick.renderer.RendererOutput;

public class MBTemplate<T> {

    private final Segment root;

    MBTemplate(Segment root) {
        this.root = root;
    }

    public String render(T context) {
        StringWriter writer = new StringWriter();
        root.render(new RendererOutput(writer), context);
        return writer.toString();
    }

    public RendererOutput render(RendererOutput out, T context) {
        root.render(out, context);
        return out;
    }
}
