package org.mb4j.example.servlet;

import com.google.inject.Inject;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.component.viewmap.ViewMap;
import org.mb4j.servlet.BrickServletFilter;

public class ServletSampleFilter extends BrickServletFilter {

    @Inject
    public ServletSampleFilter(BrickRenderer renderer, ViewMap viewMap) {
        super(renderer, viewMap);
    }
}
