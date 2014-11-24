package org.mb4j.brick;

import java.io.File;
import java.util.HashMap;
import org.junit.Test;
import org.mb4j.brick.jmustache.Mustache;
import org.mb4j.brick.jmustache.Template;

public class BrickTest {

    @Test
    public void dummy() {
        new File("asdf");
//   Template<SimpleBrick> template = Compiler.compile(SimpleBrick.class);
//   template
    }

    public static void main(String[] args) {
        Template template = Mustache.compiler().compile("aaa {{ooo}} bbb {{uuu");
        System.out.println("[" + template.execute(new HashMap() {
            {
                put("ooo", 123);
            }
        }) + "]");
    }
}
