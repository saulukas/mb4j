package org.mb4j.brick.template;

import org.mb4j.brick.jmustache.Template;

public class BrickTemplate {

    public final String name;
    public final Template template;

    public BrickTemplate(String name, Template template) {
        this.name = name;
        this.template = template;
    }
}
