package org.mb4j.brick.renderer;

import java.io.StringWriter;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.brick.template.CachedTemplateProvider;
import org.mb4j.brick.template.TemplateProviderFromClasspath;

public class RendererUtils {

    public static String renderToString4Development(MustacheBrick brick) {
        return renderToString(brick, renderer4Development());
    }

    public static BrickRenderer renderer4Development() {
        return new BrickRenderer(new TemplateProviderFromClasspath());
    }

    public static BrickRenderer renderer4Production() {
        return new BrickRenderer(new CachedTemplateProvider(new TemplateProviderFromClasspath()));
    }

    public static String renderToString(MustacheBrick brick, BrickRenderer renderer) {
        StringWriter out = new StringWriter();
        renderer.render(brick, out);
        return out.toString();
    }
}
