package org.mb4j.brick;

import java.io.Writer;
import org.mb4j.brick.jmustache.Template.Fragment;
import org.mb4j.brick.renderer.RenderingScope;

public class BrickList<T extends MustacheBrick> extends MustacheBrick {

    public final Iterable<T> items;

    public BrickList(Iterable<T> items) {
        this.items = items;
    }

    @Override
    public void execute(Fragment frag, Writer out) {
        ((RenderingScope) out).renderList(items, frag);
    }
}
