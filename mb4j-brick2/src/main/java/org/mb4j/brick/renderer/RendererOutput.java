package org.mb4j.brick.renderer;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class RendererOutput {

    public static final RendererOutput SYSTEM_OUT
            = new RendererOutput(new OutputStreamWriter(System.out));

    final Writer writer;

    public RendererOutput(Writer writer) {
        this.writer = writer;
    }

    public void flush() {
        try {
            writer.flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void write(String string) {
        write(string, false);
    }

    public void write(String string, boolean escapeHtml) {
        try {
            writer.write(string);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
