package org.mb4j.brick.compiler;

import java.io.IOException;
import java.io.Reader;

class ParserInput {

    private final Reader reader;
    int lineNo = 1;
    int colNo = 1;

    ParserInput(Reader reader) {
        this.reader = reader;
    }

    ParserLexem readNext() {
        int c = readChar();
        return null;
    }

    private int readChar() {
        try {
            return reader.read();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    String getLastToken() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
