package org.mb4j.brick.renderer;

import com.google.common.escape.Escaper;
import com.google.common.html.HtmlEscapers;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Mustache.Lambda;
import com.samskivert.mustache.Template;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.brick.samples.composition.MoreCompositeBrick;

public class BrickRendererSpeedBenchmarks {
  public static void main(String[] args) {
    //
    //      1
    //    --------
    //
    MustacheBrick brick = new MoreCompositeBrick();
    long startNanos = System.nanoTime();
    String output = "";
    for (int i = 0; i < 10 * 1000; i++) {
      output = RendererUtils.renderToString4Development(brick);
    }
    long deltaNanos = System.nanoTime() - startNanos;
//    System.out.println(output);
    System.out.println("deltaMillis = " + deltaNanos / 1000000.0);
    //
    //      2
    //    --------
    //
    long startNanos2 = System.nanoTime();
    Escaper htmlEscaper
        //        = new Escaper() {
        //          @Override
        //          public String escape(String string) {
        //            return string;
        //          }
        //        };
        = HtmlEscapers.htmlEscaper();
    for (int i = 0; i < 10 * 1000; i++) {
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
    long deltaNanos2 = System.nanoTime() - startNanos2;
    System.out.println("deltaMillis2 = " + deltaNanos2 / 1000000.0);
    //
    //      3
    //    --------
    //
    String templateString3 = "\n"
        + "    More composite brick wants to say More Composite Hello :)\n"
        + "    --------------------------------------------------------------------\n"
        + "    composite1:{{composite1}}"
        + "    --------------------------------------------------------------------\n"
        + "    composite2:{{composite2}}"
        + "    --------------------------------------------------------------------\n"
        + "    simple:{{simple}}"
        + "\n"
        + "";
    Object context3 = new Object() {
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
    Template template3 = Mustache.compiler().compile(templateString3);
    long startNanos3 = System.nanoTime();
    String actual3 = "";
    for (int i = 0; i < 10 * 1000; i++) {
      actual3 = template3.execute(context3);
    }
    long deltaNanos3 = System.nanoTime() - startNanos3;
//    System.out.println(actual3);
    System.out.println("deltaMillis3 = " + deltaNanos3 / 1000000.0);
    //
    //      4
    //    --------
    //
    String templateString4 = "\n"
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
    Object context4 = new Object() {
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
    Template template4 = Mustache.compiler().compile(templateString4);
    long startNanos4 = System.nanoTime();
    String actual4 = "";
    for (int i = 0; i < 10 * 1000; i++) {
      actual4 = template4.execute(context4);
    }
    long deltaNanos4 = System.nanoTime() - startNanos4;
//    System.out.println(actual4);
    System.out.println("deltaMillis4 = " + deltaNanos4 / 1000000.0);
  }
}
