package org.mb4j.brick.compiler;

import org.junit.Test;
import static org.mb4j.brick.compiler.MBCompiler.compile;

public class MBCompilerTest {

    private static class Brick {

        String name = "Jonas";
        int nr = 123;

        static String TEMPLATE = "Hello {{name}}! You number is {{nr}}.";
    }

    @Test
    public void testSomeMethod() {
        MBTemplate<Brick> template = compile(Brick.class, Brick.TEMPLATE);
        System.out.println(template.render(new Brick()));
    }

}
