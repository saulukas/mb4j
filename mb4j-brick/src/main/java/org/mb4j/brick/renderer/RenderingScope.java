package org.mb4j.brick.renderer;

import com.samskivert.mustache.MustacheException;
import com.samskivert.mustache.Template.Fragment;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.brick.template.BrickTemplate;
import org.mb4j.brick.template.TemplateProvider;

public class RenderingScope extends FilterWriter {
  private final TemplateProvider templateProvider;
  private String lineEnd = "\n";

  public RenderingScope(Writer out, TemplateProvider templateProvider) {
    super(out);
    this.templateProvider = templateProvider;
  }

  public void render(MustacheBrick brick) {
    brick.execute(null, this);
  }

  public void renderUnindented(MustacheBrick brick, Fragment frag) {
    String oldLineEnd = lineEnd;
    lineEnd = "\n";
    try {
      doRender(brick);
    } finally {
      lineEnd = oldLineEnd;
    }
  }

  public void renderIndented(MustacheBrick brick, Fragment frag) {
    String oldLineEnd = lineEnd;
    if (frag != null) {
      lineEnd += lineEndFrom(frag);
    }
    try {
      doRender(brick);
    } finally {
      lineEnd = oldLineEnd;
    }
  }

  public void renderList(Iterable<? extends MustacheBrick> bricks, Fragment frag) {
    Iterator<? extends MustacheBrick> iterator = bricks.iterator();
    while (iterator.hasNext()) {
      MustacheBrick brick = iterator.next();
      doRender(brick);
      if (iterator.hasNext()) {
        frag.execute(this);
      }
    }
  }

  private void doRender(MustacheBrick brick) throws MustacheException {
    BrickTemplate brickTemplate = templateProvider.templateFor(brick.getClass());
    try {
      brickTemplate.template.execute(brick, this);
    } catch (BrickRenderingException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new BrickRenderingException(brickTemplate.name + ": " + ex.getMessage(), ex);
    }
  }

  private String lineEndFrom(Fragment frag) {
    String result = frag.execute();
    int lastNewLineIndex = result.lastIndexOf('\n');
    return (lastNewLineIndex < 0) ? "" : result.substring(lastNewLineIndex + 1);
  }

  @Override
  public void write(int c) throws IOException {
    if (c == '\n') {
      out.write(lineEnd);
    } else {
      out.write(c);
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    doWrite(new String(cbuf, off, len));
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    doWrite(str.substring(off, len));
  }

  private void doWrite(String string) throws IOException {
    out.write(string.replace("\n", lineEnd));
  }
}
