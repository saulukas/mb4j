package org.mb4j.brick.compiler;

import java.io.Reader;
import java.lang.reflect.Field;
import static org.mb4j.brick.compiler.ParserLexem.END;
import static org.mb4j.brick.compiler.ParserLexem.TEXT;

public class Parser {

    private final ParserInput input;
    private final Class brickClass;

    Parser(ParserInput input, Class brickClass) {
        this.input = input;
        this.brickClass = brickClass;
    }

    public static TemplatePart parse(Reader reader, Class brickClass) {
        return new Parser(new ParserInput(reader), brickClass).parse();
    }

    public TemplatePart parse() {
        ContextPart contextPart = withLineNo(new ContextPart(brickClass));
        ParserLexem lexem = input.readLexem();
        while (lexem != END) {
            if (lexem == TEXT) {
                contextPart.partList.add(withLineNo(new TextPart(input.getLastToken())));
            } else if (lexem == ParserLexem.DELIMITER_OPEN) {
                input.readLexem();
            } else {
                throw new RuntimeException("Something wrong...");
            }
        }
        contextPart.partList.add(withLineNo(new TextPart("aaa ")));
        contextPart.partList.add(withLineNo(new FieldPart(getBrickField("var1"))));
        contextPart.partList.add(withLineNo(new TextPart(" bbb")));
        return contextPart;
    }

    <T extends TemplatePart> T withLineNo(T part) {
        part.lineNo = 1;
        part.colNo = 2;
        return part;
    }

    Field getBrickField(String name) {
        Field field;
        try {
            field = brickClass.getDeclaredField(name);
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
        } catch (NoSuchFieldException | SecurityException ex) {
            throw new RuntimeException(ex);
        }
        return field;
    }
}
