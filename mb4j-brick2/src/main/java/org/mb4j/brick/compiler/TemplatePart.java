package org.mb4j.brick.compiler;

import org.mb4j.brick.renderer.RendererOutput;
import org.mb4j.brick.renderer.RendererOutput;

public abstract class TemplatePart {

    int lineNo;
    int colNo;

    public abstract void render(RendererOutput out, Object brick);

    protected RuntimeException renderingException(String message, Throwable cause) {
        return new RuntimeException(lineNo + ":" + colNo + ": " + message, cause);
    }

}
