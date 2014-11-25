package org.mb4j.brick.renderer;

import java.io.IOException;
import java.io.Writer;

public class RendererOutput {

    final Writer writer;

    public RendererOutput(Writer writer) {
        this.writer = writer;
    }

    public void write(String string) {
        try {
            writer.write(string);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
