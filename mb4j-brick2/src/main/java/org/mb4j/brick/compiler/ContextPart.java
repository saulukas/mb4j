package org.mb4j.brick.compiler;

import java.util.ArrayList;
import java.util.List;
import org.mb4j.brick.renderer.RendererOutput;

class ContextPart extends TemplatePart {

    final Class contextClass;
    private final List<TemplatePart> partList = new ArrayList<>();

    public ContextPart(int lineNo, int colNo, Class contextClass) {
        super(lineNo, colNo);
        this.contextClass = contextClass;
    }

    @Override
    public void render(RendererOutput out, Object brick) {
        for (TemplatePart part : partList) {
            part.render(out, brick);
        }
    }

    void add(TemplatePart part) {
        partList.add(part);
    }

}
