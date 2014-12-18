package org.mb4j.brick.renderer;

import java.io.OutputStreamWriter;
import java.io.Writer;

public class SystemOut {

    public static final Writer WRITER = new OutputStreamWriter(System.out);

    public static void print(Object obj) {
        System.out.print(obj);
    }

    public static void println(Object obj) {
        System.out.println(obj);
    }
}
