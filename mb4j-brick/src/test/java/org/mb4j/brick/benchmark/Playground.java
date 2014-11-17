package org.mb4j.brick.benchmark;

import java.util.ArrayList;
import java.util.List;

public class Playground {

    static class Dog {

        String name;
        String color;

    }

    public static void main(String[] args) {
        List<Dog> dogList = new ArrayList<Dog>() {
        };
        System.out.println("ooo " + dogList.getClass());
    }

}
