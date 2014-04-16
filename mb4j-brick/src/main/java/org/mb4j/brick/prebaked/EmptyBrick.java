package org.mb4j.brick.prebaked;

import com.samskivert.mustache.Template;
import java.io.Writer;
import org.mb4j.brick.Brick;

public class EmptyBrick extends Brick {
  private static final EmptyBrick INSTANCE = new EmptyBrick();

  public static EmptyBrick emptyBrick() {
    return INSTANCE;
  }

  @Override
  public void execute(Template.Fragment frag, Writer out) {
  }
}
