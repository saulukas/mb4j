package org.mb4j.brick;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import org.mb4j.brick.internal.TemplateWriter;

public class Renderer {

    public static String render(Object brick) {
        return render("aaa {{oho}} bbb", brick);
    }

    public static String render(String templateText, Object brick) {
        return render(new StringReader(templateText), brick);
    }

    public static String render(Reader templateReader, Object brick) {
        StringWriter out = new StringWriter();
        render(out, templateReader, brick);
        return out.toString();
    }

    public static void render(Writer out, String templateText, Object brick) {
        render(out, new StringReader(templateText), brick);
    }

    public static void render(Writer out, Reader templateReader, Object brick) {
        Template template = Compiler.compile(templateReader, brick.getClass());
        template.render(new TemplateWriter(out), brick);
    }

}
