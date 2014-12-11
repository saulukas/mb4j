package org.mb4j.brick;

import java.io.Reader;
import org.mb4j.brick.compiler.Parser;

public class Compiler {

    public static <T> Template<T> compile(Class<T> brickClass) {
        return null;
    }

    public static <T> Template<T> compile(Class<T> contextClass, Reader reader) {
        return new Template<>(Parser.parse(contextClass, reader));
    }

}
