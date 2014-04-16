package org.mb4j.brick.prebaked;

import com.samskivert.mustache.Template.Fragment;
import java.io.Writer;
import org.mb4j.brick.Brick;
import org.mb4j.brick.renderer.RenderingScope;

public class BrickList extends Brick {

  public final Iterable<? extends Brick> items;

  public BrickList(Iterable<? extends Brick> items) {
    this.items = items;
  }

  @Override
  public void execute(Fragment frag, Writer out) {
    ((RenderingScope) out).renderList(items, frag);
  }

}
