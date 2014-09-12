package org.mb4j.component;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.brick.renderer.BrickRenderer;
import static org.mb4j.brick.template.TemplateUtils.outputEncodingStringOf;

public abstract class Response {
  private final BrickRenderer renderer;

  protected Response(BrickRenderer renderer) {
    this.renderer = renderer;
  }

  public abstract void setContentType(String type);

  public abstract void setContentLength(int len);

  public abstract void setCharacterEncoding(String encoding);

  public abstract Writer getWriter();

  public Response write(String string) {
    try {
      getWriter().write(string);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
    return this;
  }

  public abstract OutputStream getOutputStream();

  public BrickRenderer getRenderer() {
    return renderer;
  }

  public void render(MustacheBrick brick) {
    setCharacterEncoding(outputEncodingStringOf(brick.getClass()));
    getRenderer().render(brick, getWriter());
  }
}
