package org.mb4j.example.servlet.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestScanner {

    public static void main(String[] args) throws IOException {
        Scanner.watch(
                1,
                Arrays.asList(new File(".")),
                new Scanner.BulkListener() {
                    @Override
                    public void filesChanged(List<String> fileNames) throws Exception {
                        System.out.println("Files changed: " + new Date());
                        for (String name : fileNames) {
                            System.out.println("    " + name);
                        }
                    }
                });
    }
}
