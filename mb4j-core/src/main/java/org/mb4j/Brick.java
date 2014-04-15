package org.mb4j;

import org.mb4j.renderer.RenderingScope;
import com.samskivert.mustache.Mustache.Lambda;
import com.samskivert.mustache.Template.Fragment;
import java.io.Writer;

public class Brick implements Lambda {
  @Override
  public void execute(Fragment frag, Writer out) {
    ((RenderingScope) out).renderIndented(this, frag);
  }
}
