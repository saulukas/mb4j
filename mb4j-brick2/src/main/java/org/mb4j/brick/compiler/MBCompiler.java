package org.mb4j.brick.compiler;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;

public class MBCompiler {

    private final SegmentContext root;
    private final Reader reader;
    private final static char EOF = (char) -1;
    private char delimOpen1 = '{';
    private char delimOpen2 = '{';
    private char delimClose1 = '}';
    private char delimClose2 = '}';
    private int lineNo = 1;
    private int colNo = 0;

    MBCompiler(SegmentContext root, Reader reader) {
        this.root = root;
        this.reader = reader;
    }

    public static <T> MBTemplate<T> compile(Class<T> contextClass, String templateText) {
        return compile(contextClass, new StringReader(templateText));
    }

    public static <T> MBTemplate<T> compile(Class<T> contextClass, Reader reader) {
        return new MBCompiler(new SegmentContext(1, 1, contextClass), reader).compile();
    }

    private MBTemplate compile() {
        char c = readChar();
        parseText(c, root);
        return new MBTemplate(root);
    }

    private RuntimeException exception(String msg) {
        return exception(msg, null);
    }

    private RuntimeException exception(String msg, Throwable cause) {
        return new RuntimeException(msg, cause);
    }

    private char readChar() {
        try {
            char c = (char) reader.read();
            if (c == '\n') {
                lineNo += 1;
                colNo = 0;
            } else {
                colNo += 1;
            }
            return c;
        } catch (IOException ex) {
            throw exception("Failed to read template source", ex);
        }
    }

    private void parseText(char c, SegmentContext context) {
        StringBuilder buffer = new StringBuilder();
        while (c != EOF) {
            if (c != delimOpen1) {
                buffer.append(c);
                c = readChar();
                continue;
            }
            c = readChar();
            if (c != delimOpen2) {
                buffer.append(delimOpen1);
                continue;
            }
            c = readChar();
            if (c == '}') { // {{} - escaped opening delimiters
                buffer.append(delimOpen1);
                buffer.append(delimOpen2);
                c = readChar();
                continue;
            }
            if (c == '!') { // {{! - comment
                c = skipComment(readChar());
                continue;
            }
            String text = buffer.toString();
            if (!text.isEmpty()) {
                context.add(new SegmentText(lineNo, colNo, text));
            }
            buffer = new StringBuilder();
            c = parseAfterDelimOpen2(c, context);
        }
        String text = buffer.toString();
        if (!text.isEmpty()) {
            context.add(new SegmentText(lineNo, colNo, text));
        }
    }

    private char skipComment(char c) {
        while (true) {
            if (c == EOF) {
                throw exception("Unexpected EOF inside a comment.");
            }
            if (c != delimClose1) {
                c = readChar();
                continue;
            }
            c = readChar();
            if (c == delimClose2) {
                return readChar();
            }
        }
    }

    private char parseAfterDelimOpen2(char c, SegmentContext context) {
        if (c == EOF) {
            throw exception("Unexpected EOF after opening delimiters '" + delimOpen1 + delimOpen2 + "'.");
        }
        if (c == '&' || c == '{') {
            return parseVariable(c, context);
        }
        return parseVariable(c, context);
    }

    private char parseVariable(char c, SegmentContext context) {
        if (c == '{'
                && (delimOpen1 != '{' || delimOpen2 != '{' || delimClose1 != '}' || delimClose2 != '}')) {
            throw exception("Third '{' is only allowed with '{{' and '}}' delimiters."
                    + " Disable variable escaping with '&' instead.");
        }
        boolean tripleMustache = (c == '{');
        boolean escapeHtml = true;
        if (c == '&' || c == '{') {
            escapeHtml = false;
            c = readChar();
        }
        StringBuilder variableName = new StringBuilder();
        while (true) {
            if (c == EOF) {
                throw exception("Unexpected EOF wile reading variable name.");
            }
            if (c == '\n') {
                throw exception("Unexpected EOL wile reading variable name.");
            }
            if (c != delimClose1) {
                variableName.append(c);
                c = readChar();
                continue;
            }
            c = readChar();
            if (c != delimClose2) {
                variableName.append(delimClose1);
                c = readChar();
                continue;
            }
            if (tripleMustache) {
                c = readChar();
                if (c != '}') {
                    throw exception("Expected '}}}' after variable name.");
                }
            }
            String fieldName = variableName.toString();
            if (fieldName.isEmpty()) {
                throw exception("Expected variable name between delimiters.");
            }
            Field field = getField(context.contextClass, fieldName);
            context.add(new SegmentField(lineNo, colNo, escapeHtml, field));
            return readChar();
        }
    }

    private Field getField(Class klass, String fieldName) {
        Field field;
        try {
            field = klass.getDeclaredField(fieldName);
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
        } catch (NoSuchFieldException | SecurityException ex) {
            throw exception("Can not access field '" + fieldName + "' of class " + klass, ex);
        }
        return field;
    }

}
