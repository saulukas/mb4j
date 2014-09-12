package org.mb4j.brick.samples.layout;

import org.mb4j.brick.EmptyBrick;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.brick.template.TemplateType;

@TemplateType(".mustache")
public class LayoutBrick extends MustacheBrick {

    MustacheBrick header = new HeaderBrick();
    MustacheBrick footer = new FooterBrick();
    MustacheBrick content = new EmptyBrick();
}
