package org.mb4j.brick.internal;

import java.util.ArrayList;
import java.util.List;

class ContextPart extends TemplatePart {

    final Class brickClass;
    final List<TemplatePart> partList = new ArrayList<>();

    public ContextPart(Class brickClass) {
        this.brickClass = brickClass;
    }

    @Override
    public void render(TemplateWriter out, Object brick) {
        for (TemplatePart part : partList) {
            part.render(out, brick);
        }
    }

}
