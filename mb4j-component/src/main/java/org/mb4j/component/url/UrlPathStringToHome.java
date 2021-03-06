package org.mb4j.component.url;

public class UrlPathStringToHome {

    public static String from(String pathString) {
        int slashCount = 0;
        for (int i = 0; i < pathString.length(); i++) {
            if (i != 0 && pathString.charAt(i) == '/') {
                slashCount += 1;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < slashCount; i++) {
            result.append("../");
        }
        return result.toString();
    }
}
