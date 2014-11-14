package org.mb4j.brick;

import java.io.Writer;
import org.mb4j.brick.jmustache.Template;

public class EmptyBrick extends MustacheBrick {

    private static final EmptyBrick INSTANCE = new EmptyBrick();

    public static EmptyBrick emptyBrick() {
        return INSTANCE;
    }

    @Override
    public void execute(Template.Fragment frag, Writer out) {
    }
}
