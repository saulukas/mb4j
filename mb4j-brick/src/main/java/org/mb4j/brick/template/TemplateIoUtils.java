package org.mb4j.brick.template;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.annotation.Nullable;

public class TemplateIoUtils {

    public static InputStream inputStreamOrNull(@Nullable Class klass, String fileType) {
        if (klass == null) {
            return null;
        }
        String resourcePath = "/" + klass.getName().replace(".", "/") + fileType;
        return klass.getResourceAsStream(resourcePath);
    }

    public static String readAllCharsFrom(InputStream in, String charsetName) {
        try {
            return new String(readAllBytesFrom(in), charsetName);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Failed to read all chars from input stream: " + ex);
        }
    }

    private static byte[] readAllBytesFrom(InputStream in) {
        try {
            int byteCount = in.available();
            byte[] buffer = new byte[byteCount];
            try {
                int bytesRead = 0;
                while (bytesRead < byteCount) {
                    bytesRead += in.read(buffer, bytesRead, byteCount - bytesRead);
                }
            } finally {
                in.close();
            }
            return buffer;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to read all bytes from input stream: " + ex, ex);
        }
    }
}
