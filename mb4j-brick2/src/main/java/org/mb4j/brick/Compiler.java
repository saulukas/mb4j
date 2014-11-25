package org.mb4j.brick;

import java.io.Reader;
import org.mb4j.brick.internal.Parser;

public class Compiler<T> {

    public static <T> Template<T> compile(Class<T> brickClass) {
        return null;
    }

    public static <T> Template<T> compile(Reader reader, Class<T> brickClass) {
        Parser parser = new Parser(reader, brickClass);
        return null;
    }

}
