package org.mb4j.brick;

import java.io.Writer;
import org.mb4j.brick.jmustache.Mustache.Lambda;
import org.mb4j.brick.jmustache.Template.Fragment;
import org.mb4j.brick.renderer.RenderingScope;

public class MustacheBrick implements Lambda {

    @Override
    public void execute(Fragment frag, Writer out) {
        ((RenderingScope) out).renderIndented(this, frag);
    }
}
