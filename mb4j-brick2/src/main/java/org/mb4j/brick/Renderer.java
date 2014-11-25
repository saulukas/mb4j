package org.mb4j.brick;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class Renderer {
  public static String render(Object brick) {
    return render("aaa {{oho}} bbb", brick);
  }

  public static String render(String templateText, Object brick) {
    StringWriter out = new StringWriter(templateText.length());
    render(out, templateText, brick);
    return out.toString();
  }

  public static void render(Writer out, String templateText, Object brick) {
    try {
      out.write("aaa 123 bbb");
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
