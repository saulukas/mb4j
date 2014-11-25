package org.mb4j.brick.internal;

import java.lang.reflect.Field;

class FieldPart extends TemplatePart {

    final Field field;

    public FieldPart(Field field) {
        this.field = field;
    }

    @Override
    public void render(TemplateWriter out, Object brick) {
        Object fieldValue = null;
        try {
            fieldValue = field.get(brick);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw renderingException("Failed to access field '" + field.getName() + "'", ex);
        }
        if (fieldValue != null) {
            out.write(fieldValue.toString());
        }
    }

}
