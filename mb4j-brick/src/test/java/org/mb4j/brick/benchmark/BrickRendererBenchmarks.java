package org.mb4j.brick.benchmark;

import com.google.common.escape.Escaper;
import com.google.common.html.HtmlEscapers;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.mb4j.brick.MustacheBrick;
import static org.mb4j.brick.benchmark.TextUtils.add1000seps;
import static org.mb4j.brick.benchmark.TextUtils.alignLeft;
import static org.mb4j.brick.benchmark.TextUtils.alignRight;
import static org.mb4j.brick.benchmark.TextUtils.fillChar;
import org.mb4j.brick.jmustache.Mustache;
import org.mb4j.brick.jmustache.Mustache.Lambda;
import org.mb4j.brick.jmustache.Template;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.brick.renderer.RendererUtils;
import org.mb4j.brick.samples.composition.MoreCompositeBrick;

public class BrickRendererBenchmarks {

    static final int MAX_THREAD_COUNT = 2;
    static final int RENDER_COUNT = 100_000;
    static final int NAME_WIDTH = 16;
    static final int FREQUENCY_WIDTH = 12;
    static final int MILLIS_WIDTH = 7;

    public static void main(String[] args) throws Exception {
        System.out.println("Threads      : " + MAX_THREAD_COUNT);
        System.out.println("render count : " + RENDER_COUNT);
        System.out.println(fillChar('-', NAME_WIDTH)
                + " " + alignRight("Frequency", FREQUENCY_WIDTH)
                + " " + alignRight("Millis", MILLIS_WIDTH)
        );
        //render4development();
        render4production();
        renderUsingObjectAttributes();
        renderUsingObjectAttributes2();
        renderUsingLambdas();
        renderUsingPlainWrite();
        renderUsingReflection();
        renderUsingReflection2();
        renderUsingReflectionGetters();
        renderUsingGuavaEscapers();
    }

    static class BenchmarkRunnable implements Runnable {

        final Runnable task;
        final int repeatCount;

        public BenchmarkRunnable(Runnable task, int repeatCount) {
            this.task = task;
            this.repeatCount = repeatCount;
        }

        @Override
        public void run() {
            for (int i = 0; i < repeatCount; i++) {
                task.run();
            }
        }

    }

    static void benchmark(String info, final int count, final Runnable runnable) {
        System.out.print(alignLeft(" " + info, NAME_WIDTH));
        long startNanos = System.nanoTime();
        if (MAX_THREAD_COUNT == 1) {
            new BenchmarkRunnable(runnable, count).run();
        } else {
            int countLeft = count;
            List<Thread> threadList = new ArrayList<>(MAX_THREAD_COUNT);
            int repeatCount = (count / MAX_THREAD_COUNT) + (count % MAX_THREAD_COUNT);
            while (countLeft > 0) {
                countLeft -= repeatCount;
                threadList.add(new Thread(new BenchmarkRunnable(runnable, repeatCount)));
                repeatCount = (count / MAX_THREAD_COUNT);
            }
            startNanos = System.nanoTime();
            for (Thread thread : threadList) {
                thread.start();
            }
            try {
                for (Thread thread : threadList) {
                    thread.join();
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        long deltaNanos = System.nanoTime() - startNanos;
        double millis = deltaNanos / 1000_000.0;
        double secs = millis / 1000.0;
        long frequency = (long) (count / secs);
        System.out.println(""
                + " " + alignRight(add1000seps(frequency + ""), FREQUENCY_WIDTH)
                + " " + alignRight(add1000seps((long) millis + ""), MILLIS_WIDTH)
        );

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
        final String[] output = new String[]{""};
        benchmark("production", RENDER_COUNT, new Runnable() {

            @Override
            public void run() {
                Writer writer = new StringWriter();
                renderer.render(brick, writer);
                output[0] = writer.toString();
            }
        });
        System.out.print(output[0]);
    }

    private static void renderUsingGuavaEscapers() {
        final Escaper htmlEscaper = HtmlEscapers.htmlEscaper();
        benchmark("guava escaper", RENDER_COUNT, new Runnable() {

            @Override
            public void run() {
                StringWriter writer = new StringWriter();
                writer.write(""
                        + "\n"
                        + "    More composite brick wants to say More Composite Hello :)\n"
                        + "    --------------------------------------------------------------------\n"
                        + "    composite1:");
                writer.write(htmlEscaper.escape(""
                        + " \n"
                        + "    \n"
                        + "        Composite brick wants to say Composite Hello 1 :)\n"
                        + "    \n"
                        + "        1. Just wanted to say First Hello :)\n"
                        + "        2. Just wanted to say Second Hello :)\n"
                        + "    \n"
                ));
                writer.write(""
                        + "    --------------------------------------------------------------------\n"
                        + "    composite2:"
                );
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

    private static void renderUsingReflection() {
        final String templateString1 = "\n"
                + "    More composite brick wants to say More Composite Hello :)\n"
                + "    --------------------------------------------------------------------\n"
                + "    composite1:";
        final String templateString2 = "" // {{composite1}}"
                + "    --------------------------------------------------------------------\n"
                + "    composite2:";
        final String templateString3 = "" // {{composite2}}"
                + "    --------------------------------------------------------------------\n"
                + "    simple:";
        final String templateString4 = "" //  "{{simple}}"
                + "\n"
                + "";
        final Object context = new Object() {
            public String composite1 = ""
                    + " \n"
                    + "    \n"
                    + "        Composite brick wants to say Composite Hello 1 :)\n"
                    + "    \n"
                    + "        1. Just wanted to say First Hello :)\n"
                    + "        2. Just wanted to say Second Hello :)\n"
                    + "    \n";
            public String composite2 = ""
                    + " \n"
                    + "    -----------     Composite brick wants to say Composite Hello 2 :)\n"
                    + "    ----------- \n"
                    + "    -----------     1. Just wanted to say First Hello :)\n"
                    + "    -----------     2. Just wanted to say Second Hello :)\n"
                    + "    ----------- \n";
            public String simple = ""
                    + " Just wanted to say Simple Hello3 :)\n";
        };

        benchmark("reflection", RENDER_COUNT, new Runnable() {

            @Override
            public void run() {
                StringWriter writer = new StringWriter();
                try {
                    writer.write(templateString1);
                    writer.write(context.getClass().getDeclaredField("composite1").get(context).toString());
                    writer.write(templateString2);
                    writer.write(context.getClass().getDeclaredField("composite2").get(context).toString());
                    writer.write(templateString3);
                    writer.write(context.getClass().getDeclaredField("simple").get(context).toString());
                    writer.write(templateString4);
                } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    static class Data {

        static String templateString1 = "\n"
                + "    More composite brick wants to say More Composite Hello :)\n"
                + "    --------------------------------------------------------------------\n"
                + "    composite1:";
        static String templateString2 = "" // {{composite1}}"
                + "    --------------------------------------------------------------------\n"
                + "    composite2:";
        static String templateString3 = "" // {{composite2}}"
                + "    --------------------------------------------------------------------\n"
                + "    simple:";
        static String templateString4 = "" //  "{{simple}}"
                + "\n"
                + "";
        static Object context = new Object() {
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
                    + "    -----------     These will be escaped [<>\"\'] \n"
                    + "    -----------     1. Just wanted to say First Hello :)\n"
                    + "    -----------     2. Just wanted to say Second Hello :)\n"
                    + "    ----------- \n";
            String simple = ""
                    + " Just wanted to say Simple Hello3 :)\n";
        };
    }

    private static void renderUsingReflection2() throws Exception {

        final Field composite1 = Data.context.getClass().getDeclaredField("composite1");
        composite1.setAccessible(true);
        final Field composite2 = Data.context.getClass().getDeclaredField("composite2");
        composite2.setAccessible(true);
        final Field simple = Data.context.getClass().getDeclaredField("simple");
        simple.setAccessible(true);

        final Escaper htmlEscaper = HtmlEscapers.htmlEscaper();

        benchmark("reflection2", RENDER_COUNT, new Runnable() {

            @Override
            public void run() {
                StringWriter writer = new StringWriter();
                try {
                    writer.write(Data.templateString1);
                    writer.write(htmlEscaper.escape(composite1.get(Data.context).toString()));
                    writer.write(Data.templateString2);
                    writer.write(htmlEscaper.escape(composite2.get(Data.context).toString()));
                    writer.write(Data.templateString3);
                    writer.write(htmlEscaper.escape(simple.get(Data.context).toString()));
                    writer.write(Data.templateString4);
                } catch (SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private static void renderUsingReflectionGetters() throws Exception {
        final String templateString1 = "\n"
                + "    More composite brick wants to say More Composite Hello :)\n"
                + "    --------------------------------------------------------------------\n"
                + "    composite1:";
        final String templateString2 = "" // {{composite1}}"
                + "    --------------------------------------------------------------------\n"
                + "    composite2:";
        final String templateString3 = "" // {{composite2}}"
                + "    --------------------------------------------------------------------\n"
                + "    simple:";
        final String templateString4 = "" //  "{{simple}}"
                + "\n"
                + "";
        final Object context = new Object() {
            public String getComposite1() {
                return ""
                        + " \n"
                        + "    \n"
                        + "        Composite brick wants to say Composite Hello 1 :)\n"
                        + "    \n"
                        + "        1. Just wanted to say First Hello :)\n"
                        + "        2. Just wanted to say Second Hello :)\n"
                        + "    \n";
            }

            public String getComposite2() {
                return ""
                        + " \n"
                        + "    -----------     Composite brick wants to say Composite Hello 2 :)\n"
                        + "    ----------- \n"
                        + "    -----------     1. Just wanted to say First Hello :)\n"
                        + "    -----------     2. Just wanted to say Second Hello :)\n"
                        + "    ----------- \n";
            }

            public String getSimple() {
                return " Just wanted to say Simple Hello3 :)\n";
            }
        };

        final Method composite1 = context.getClass().getDeclaredMethod("getComposite1");
        final Method composite2 = context.getClass().getDeclaredMethod("getComposite2");
        final Method simple = context.getClass().getDeclaredMethod("getSimple");

        final Escaper htmlEscaper = //HtmlEscapers.htmlEscaper();
                new Escaper() {

                    @Override
                    public String escape(String string) {
                        return string;
                    }
                };
        benchmark("refl getters", RENDER_COUNT, new Runnable() {

            @Override
            public void run() {
                StringWriter writer = new StringWriter();
                try {
                    writer.write(templateString1);
                    writer.write(htmlEscaper.escape(composite1.invoke(context).toString()));
                    writer.write(templateString2);
                    writer.write(htmlEscaper.escape(composite2.invoke(context).toString()));
                    writer.write(templateString3);
                    writer.write(htmlEscaper.escape(simple.invoke(context).toString()));
                    writer.write(templateString4);
                } catch (InvocationTargetException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private static void renderUsingObjectAttributes() {
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

        final Template template = Mustache.compiler().compile(templateString);
        benchmark("object attrs", RENDER_COUNT, new Runnable() {

            @Override
            public void run() {
                StringWriter writer = new StringWriter();
                template.execute(Data.context, writer);
            }
        });
    }

    private static void renderUsingObjectAttributes2() {
        String templateString = "\n"
                + "    More composite brick wants to say More Composite Hello :)\n"
                + "    --------------------------------------------------------------------\n"
                + "    composite1:{{composite1}}{{simple}}{{simple}}"
                + "    --------------------------------------------------------------------\n"
                + "    composite2:{{composite2}}{{simple}}{{simple}}"
                + "    --------------------------------------------------------------------\n"
                + "    simple:{{simple}}"
                + "\n"
                + "";

        final Template template = Mustache.compiler().compile(templateString);
        benchmark("object attrs2", RENDER_COUNT, new Runnable() {

            @Override
            public void run() {
                StringWriter writer = new StringWriter();
                template.execute(Data.context, writer);
            }
        });
    }

    private static void renderUsingObjectAttributes3() {
        String templateString = "\n"
                + "    More composite brick wants to say More Composite Hello :)\n"
                + "    --------------------------------------------------------------------\n"
                + "    composite1:{{{composite1}}}"
                + "    --------------------------------------------------------------------\n"
                + "    composite2:{{{composite2}}}"
                + "    --------------------------------------------------------------------\n"
                + "    simple:{{{simple}}}"
                + "\n"
                + "";
        final Template template = Mustache.compiler().compile(templateString);
        benchmark("object attrs3", RENDER_COUNT, new Runnable() {

            @Override
            public void run() {
                StringWriter writer = new StringWriter();
                template.execute(Data.context, writer);
            }
        });
    }

    private static void renderUsingLambdas() {
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
        final String[] output = new String[]{""};
        benchmark("lambdas", RENDER_COUNT, new Runnable() {

            @Override
            public void run() {
                StringWriter writer = new StringWriter();
                template.execute(context, writer);
                //   output[0] = writer.toString();
            }
        });
        System.out.print(output[0]);
    }

    private static void renderUsingPlainWrite() {
        benchmark("plain write", RENDER_COUNT, new Runnable() {

            @Override
            public void run() {
                StringWriter writer = new StringWriter();
                writer.write(""
                        + "\n"
                        + "    More composite brick wants to say More Composite Hello :)\n"
                        + "    --------------------------------------------------------------------\n"
                        + "    composite1:");
                writer.write(""
                        + " \n"
                        + "    \n"
                        + "        Composite brick wants to say Composite Hello 1 :)\n"
                        + "    \n"
                        + "        1. Just wanted to say First Hello :)\n"
                        + "        2. Just wanted to say Second Hello :)\n"
                        + "    \n"
                );
                writer.write(""
                        + "    --------------------------------------------------------------------\n"
                        + "    composite2:"
                );
                writer.write(""
                        + " \n"
                        + "    -----------     Composite brick wants to say Composite Hello 2 :)\n"
                        + "    ----------- \n"
                        + "    -----------     1. Just wanted to say First Hello :)\n"
                        + "    -----------     2. Just wanted to say Second Hello :)\n"
                        + "    ----------- \n"
                        + "    --------------------------------------------------------------------\n"
                        + "    simple:"
                );
                writer.write(""
                        + " Just wanted to say Simple Hello2 :)\n"
                        + "\n"
                );
            }
        });
    }

}
