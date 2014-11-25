package org.mb4j.brick.compiler;

import org.mb4j.brick.renderer.RendererOutput;

class TextPart extends TemplatePart {

    final String text;

    public TextPart(String text) {
        this.text = text;
    }

    @Override
    public void render(RendererOutput out, Object brick) {
        out.write(text);
    }

}
