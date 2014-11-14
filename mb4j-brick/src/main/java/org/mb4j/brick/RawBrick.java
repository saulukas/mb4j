package org.mb4j.brick;

import java.io.IOException;
import java.io.Writer;
import org.mb4j.brick.jmustache.Template;

/**
 * Does not html-escape rawText.
 */
public class RawBrick extends MustacheBrick {

    public final String rawText;

    public RawBrick(String rawText) {
        this.rawText = rawText;
    }

    @Override
    public void execute(Template.Fragment frag, Writer out) {
        try {
            out.write(rawText);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
