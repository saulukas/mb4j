package org.mb4j.brick.template;

import com.samskivert.mustache.Template;

public class BrickTemplate {

    public final String name;
    public final Template template;

    public BrickTemplate(String name, Template template) {
        this.name = name;
        this.template = template;
    }
}
