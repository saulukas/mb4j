package org.mb4j.brick;

import com.samskivert.mustache.Template.Fragment;
import java.io.Writer;
import org.mb4j.brick.renderer.RenderingScope;

public class BrickList extends MustacheBrick {
  public final Iterable<? extends MustacheBrick> items;

  public BrickList(Iterable<? extends MustacheBrick> items) {
    this.items = items;
  }

  @Override
  public void execute(Fragment frag, Writer out) {
    ((RenderingScope) out).renderList(items, frag);
  }
}
