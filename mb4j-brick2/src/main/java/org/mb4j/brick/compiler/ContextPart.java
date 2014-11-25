package org.mb4j.brick.compiler;

import java.util.ArrayList;
import java.util.List;
import org.mb4j.brick.renderer.RendererOutput;

class ContextPart extends TemplatePart {

    final Class brickClass;
    final List<TemplatePart> partList = new ArrayList<>();

    public ContextPart(Class brickClass) {
        this.brickClass = brickClass;
    }

    @Override
    public void render(RendererOutput out, Object brick) {
        for (TemplatePart part : partList) {
            part.render(out, brick);
        }
    }

}
