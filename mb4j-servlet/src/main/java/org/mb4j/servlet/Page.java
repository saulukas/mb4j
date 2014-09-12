package org.mb4j.servlet;

import org.mb4j.brick.EmptyBrick;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.BrickBaker;
import org.mb4j.component.ComponentUsingReflection;
import org.mb4j.component.Controller;
import org.mb4j.component.Request;
import org.mb4j.component.Response;

public class Page extends ComponentUsingReflection implements Controller, BrickBaker {

    @Override
    public void handle(Request request, Response response) {
        response.render(bakeBrick(request));
    }

    @Override
    public MustacheBrick bakeBrick(Request request) {
        return EmptyBrick.emptyBrick();
    }
}
