package org.mb4j.brick.compiler;

import java.lang.reflect.Field;
import org.mb4j.brick.renderer.RendererOutput;

class FieldPart extends TemplatePart {

    private final boolean escapeHtml;
    private final Field field;

    public FieldPart(int lineNo, int colNo, boolean escapeHtml, Field field) {
        super(lineNo, colNo);
        this.escapeHtml = escapeHtml;
        this.field = field;
    }

    @Override
    public void render(RendererOutput out, Object brick) {
        Object fieldValue = null;
        try {
            fieldValue = field.get(brick);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw renderingException("Failed to access field '" + field.getName() + "'", ex);
        }
        if (fieldValue != null) {
            out.write(fieldValue.toString(), escapeHtml);
        }
    }

}
