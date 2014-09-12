package org.mb4j.brick;

import com.samskivert.mustache.Template.Fragment;
import java.io.Writer;
import org.mb4j.brick.renderer.RenderingScope;

public class UnindentedBrick extends MustacheBrick {

    @Override
    public void execute(Fragment frag, Writer out) {
        ((RenderingScope) out).renderUnindented(this, frag);
    }
}
