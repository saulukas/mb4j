package org.mb4j.servlet.sample;

import com.google.inject.Inject;
import org.mb4j.brick.BrickRenderer;
import org.mb4j.servlet.BrickServletFilter;
import org.mb4j.controller.ViewMap;

public class ServletSampleFilter extends BrickServletFilter {
    @Inject
    public ServletSampleFilter(BrickRenderer renderer, ViewMap viewMap) {
        super(renderer, viewMap);
    }
}
