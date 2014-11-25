package org.mb4j.brick.internal;

public abstract class TemplatePart {

    int lineNo;
    int colNo;

    public abstract void render(TemplateWriter out, Object brick);

    protected RuntimeException renderingException(String message, Throwable cause) {
        return new RuntimeException(lineNo + ":" + colNo + ": " + message, cause);
    }

}
