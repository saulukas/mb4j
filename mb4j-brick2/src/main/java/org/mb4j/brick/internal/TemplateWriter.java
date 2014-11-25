package org.mb4j.brick.internal;

import java.io.IOException;
import java.io.Writer;

public class TemplateWriter {

    final Writer writer;

    public TemplateWriter(Writer writer) {
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
