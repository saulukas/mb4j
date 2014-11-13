package org.mb4j.brick.renderer;

import com.google.common.escape.Escaper;
import com.google.common.html.HtmlEscapers;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Mustache.Lambda;
import com.samskivert.mustache.MustacheException;
import com.samskivert.mustache.Template;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.brick.samples.composition.MoreCompositeBrick;

public class BrickRendererBenchmarks {

    static final int RENDER_COUNT = 10000;

    public static void main(String[] args) {
        System.out.println("render count: " + RENDER_COUNT);
        //render4development();
        render4production();
        renderUsingGuavaEscapers();
        renderUsingObjectAttributes();
        renderUsingLambdas();
    }

    static void benchmark(String info, int count, Runnable runnable) {
        System.out.print(info);
        long startNanos = System.nanoTime();
        for (int i = 0; i < count; i++) {
            runnable.run();
        }
        long deltaNanos = System.nanoTime() - startNanos;
        double millis = deltaNanos / 1000_000.0;
        double secs = millis / 1000.0;
        long frequency = (long) (count / secs);
        System.out.println("\t" + "freq=" + frequency + "\t" + "millis=" + millis);

    }

    private static void render4development() {
        final MustacheBrick brick = new MoreCompositeBrick();
        final BrickRenderer renderer = RendererUtils.renderer4Development();
        benchmark("development: ", RENDER_COUNT, new Runnable() {

            @Override
            public void run() {
                Writer writer = new StringWriter();
                renderer.render(brick, writer);
            }
        });
    }

    private static void render4production() {
        final MustacheBrick brick = new MoreCompositeBrick();
        final BrickRenderer renderer = RendererUtils.renderer4Production();
        benchmark("production: ", RENDER_COUNT, new Runnable() {

            @Override
            public void run() {
                Writer writer = new StringWriter();
                renderer.render(brick, writer);
            }
        });
    }

    private static void renderUsingGuavaEscapers() {
        final Escaper htmlEscaper = HtmlEscapers.htmlEscaper();
        benchmark("guava escaper: ", RENDER_COUNT, new Runnable() {

            @Override
            public void run() {
                StringWriter writer = new StringWriter();
                writer.write(htmlEscaper.escape(""
                        + "\n"
                        + "    More composite brick wants to say More Composite Hello :)\n"
                        + "    --------------------------------------------------------------------\n"
                        + "    composite1:"));
                writer.write(htmlEscaper.escape(""
                        + " \n"
                        + "    \n"
                        + "        Composite brick wants to say Composite Hello 1 :)\n"
                        + "    \n"
                        + "        1. Just wanted to say First Hello :)\n"
                        + "        2. Just wanted to say Second Hello :)\n"
                        + "    \n"
                ));
                writer.write(htmlEscaper.escape(""
                        + "    --------------------------------------------------------------------\n"
                        + "    composite2:"
                ));
                writer.write(htmlEscaper.escape(""
                        + " \n"
                        + "    -----------     Composite brick wants to say Composite Hello 2 :)\n"
                        + "    ----------- \n"
                        + "    -----------     1. Just wanted to say First Hello :)\n"
                        + "    -----------     2. Just wanted to say Second Hello :)\n"
                        + "    ----------- \n"
                        + "    --------------------------------------------------------------------\n"
                        + "    simple:"
                ));
                writer.write(htmlEscaper.escape(""
                        + " Just wanted to say Simple Hello2 :)\n"
                        + "\n"
                ));
            }
        });
    }

    private static void renderUsingObjectAttributes() throws MustacheException {
        String templateString = "\n"
                + "    More composite brick wants to say More Composite Hello :)\n"
                + "    --------------------------------------------------------------------\n"
                + "    composite1:{{composite1}}"
                + "    --------------------------------------------------------------------\n"
                + "    composite2:{{composite2}}"
                + "    --------------------------------------------------------------------\n"
                + "    simple:{{simple}}"
                + "\n"
                + "";
        final Object context = new Object() {
            String composite1 = ""
                    + " \n"
                    + "    \n"
                    + "        Composite brick wants to say Composite Hello 1 :)\n"
                    + "    \n"
                    + "        1. Just wanted to say First Hello :)\n"
                    + "        2. Just wanted to say Second Hello :)\n"
                    + "    \n";
            String composite2 = ""
                    + " \n"
                    + "    -----------     Composite brick wants to say Composite Hello 2 :)\n"
                    + "    ----------- \n"
                    + "    -----------     1. Just wanted to say First Hello :)\n"
                    + "    -----------     2. Just wanted to say Second Hello :)\n"
                    + "    ----------- \n";
            String simple = ""
                    + " Just wanted to say Simple Hello3 :)\n";
        };
        final Template template = Mustache.compiler().compile(templateString);
        benchmark("object attrs: ", RENDER_COUNT, new Runnable() {

            @Override
            public void run() {
                template.execute(context);
            }
        });
    }

    private static void renderUsingLambdas() throws MustacheException {
        String templateString = "\n"
                + "    More composite brick wants to say More Composite Hello :)\n"
                + "    --------------------------------------------------------------------\n"
                + "    composite1: {{#composite1}}"
                + "                {{/composite1}}"
                + "    --------------------------------------------------------------------\n"
                + "    composite2: {{#composite2}}"
                + "                {{/composite2}}"
                + "    --------------------------------------------------------------------\n"
                + "    simple: {{#simple}}{{/simple}}"
                + "\n"
                + "";
        final Object context = new Object() {
            Lambda composite1 = new Lambda() {
                @Override
                public void execute(Template.Fragment frag, Writer out) throws IOException {
                    out.write(""
                            + " \n"
                            + "    \n"
                            + "        Composite brick wants to say Composite Hello 1 :)\n"
                            + "    \n"
                            + "        1. Just wanted to say First Hello :)\n"
                            + "        2. Just wanted to say Second Hello :)\n"
                            + "    \n"
                    );
                }
            };
            Lambda composite2 = new Lambda() {
                @Override
                public void execute(Template.Fragment frag, Writer out) throws IOException {
                    out.write(""
                            + " \n"
                            + "    -----------     Composite brick wants to say Composite Hello 2 :)\n"
                            + "    ----------- \n"
                            + "    -----------     1. Just wanted to say First Hello :)\n"
                            + "    -----------     2. Just wanted to say Second Hello :)\n"
                            + "    ----------- \n"
                    );
                }
            };
            Lambda simple = new Lambda() {
                @Override
                public void execute(Template.Fragment frag, Writer out) throws IOException {
                    out.write(""
                            + " \n"
                            + " Just wanted to say Simple Hello4 :)\n"
                    );
                }
            };
        };
        final Template template = Mustache.compiler().compile(templateString);
        benchmark("lambdas: ", RENDER_COUNT, new Runnable() {

            @Override
            public void run() {
                template.execute(context);
            }
        });
    }
}
