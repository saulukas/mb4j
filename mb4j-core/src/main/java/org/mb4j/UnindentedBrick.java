package org.mb4j;

import org.mb4j.renderer.RenderingScope;
import com.samskivert.mustache.Template.Fragment;
import java.io.Writer;

public class UnindentedBrick extends Brick {
  @Override
  public void execute(Fragment frag, Writer out) {
    ((RenderingScope) out).renderUnindented(this, frag);
  }
}
