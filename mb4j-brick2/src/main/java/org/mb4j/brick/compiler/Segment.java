package org.mb4j.brick.compiler;

import org.mb4j.brick.renderer.RendererOutput;

abstract class Segment {

    int lineNo;
    int colNo;

    public Segment(int lineNo, int colNo) {
        this.lineNo = lineNo;
        this.colNo = colNo;
    }

    public abstract void render(RendererOutput out, Object context);

    protected RuntimeException renderingException(String message, Throwable cause) {
        return new RuntimeException(lineNo + ":" + colNo + ": " + message, cause);
    }

}
