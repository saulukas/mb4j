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

    ParserLexem readLexem() {
        int c = readChar();
        return null;
    }

    String readTagKey() {
        StringBuffer result = new StringBuffer();
        int c = readChar();
        if (!isFirstCharOfIdentifier(c)) {
            throw parserException("Invalid first character of tag key: " + (char) c);
        }
        result.append((char) c);
        c = readChar();
        while (isTailCharOfIdentifier(c)) {
            result.append((char) c);
        }
        return result.toString();
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

    boolean isFirstCharOfIdentifier(int c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    boolean isTailCharOfIdentifier(int c) {
        return isFirstCharOfIdentifier(c) || isDigit(c) || c == '.';
    }

    boolean isDigit(int c) {
        return c >= '0' && c <= '9';
    }

    RuntimeException parserException(String message) {
        return new RuntimeException(message);
    }

}
