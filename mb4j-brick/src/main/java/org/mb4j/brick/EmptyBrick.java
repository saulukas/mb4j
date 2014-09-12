package org.mb4j.brick;

import com.samskivert.mustache.Template;
import java.io.Writer;

public class EmptyBrick extends MustacheBrick {

    private static final EmptyBrick INSTANCE = new EmptyBrick();

    public static EmptyBrick emptyBrick() {
        return INSTANCE;
    }

    @Override
    public void execute(Template.Fragment frag, Writer out) {
    }
}
