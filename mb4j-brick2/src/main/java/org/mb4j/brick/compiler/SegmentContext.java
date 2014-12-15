package org.mb4j.brick.compiler;

import java.util.ArrayList;
import java.util.List;
import org.mb4j.brick.renderer.RendererOutput;

class SegmentContext extends Segment {

    final Class contextClass;
    private final List<Segment> partList = new ArrayList<>();

    public SegmentContext(int lineNo, int colNo, Class contextClass) {
        super(lineNo, colNo);
        this.contextClass = contextClass;
    }

    @Override
    public void render(RendererOutput out, Object brick) {
        for (Segment part : partList) {
            part.render(out, brick);
        }
    }

    void add(Segment part) {
        partList.add(part);
    }

}
