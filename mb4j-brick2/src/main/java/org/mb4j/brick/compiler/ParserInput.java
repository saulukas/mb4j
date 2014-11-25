package org.mb4j.brick.compiler;

import java.io.Reader;

class ParserInput {

    private final Reader reader;
    int lineNo = 1;
    int colNo = 1;

    ParserInput(Reader reader) {
        this.reader = reader;
    }

}
