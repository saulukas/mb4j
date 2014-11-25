package org.mb4j.brick.internal;

class TextPart extends TemplatePart {

    final String text;

    public TextPart(String text) {
        this.text = text;
    }

    @Override
    public void render(TemplateWriter out, Object brick) {
        out.write(text);
    }

}
