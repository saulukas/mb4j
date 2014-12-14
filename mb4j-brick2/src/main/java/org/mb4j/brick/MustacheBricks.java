package org.mb4j.brick;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import org.mb4j.brick.compiler.MBTemplate;
import org.mb4j.brick.compiler.MBCompiler;
import org.mb4j.brick.renderer.RendererOutput;

public class MustacheBricks {

    public static <T> MBTemplate<T> compile(Class<T> brickClass) {
        return null;
    }

    public static <T> MBTemplate<T> compile(Class<T> contextClass, Reader reader) {
        return MBCompiler.compile(contextClass, reader);
    }

    public static String render(Object brick) {
        return render("aaa {{oho}} bbb", brick);
    }

    public static String render(String templateText, Object context) {
        return render(new StringReader(templateText), context);
    }

    public static String render(Reader templateReader, Object context) {
        StringWriter out = new StringWriter();
        render(out, templateReader, context);
        return out.toString();
    }

    public static void render(Writer out, String templateText, Object context) {
        render(out, new StringReader(templateText), context);
    }

    public static void render(Writer out, Reader templateReader, Object context) {
        MBTemplate template = MBCompiler.compile(context.getClass(), templateReader);
        template.render(new RendererOutput(out), context);
    }

}
