package org.mb4j.liferay;

import org.mb4j.brick.EmptyBrick;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.BrickBaker;
import org.mb4j.component.ReflectiveComponent;
import org.mb4j.component.View;
import org.mb4j.component.Request;
import org.mb4j.component.Response;

public class PortletView extends ReflectiveComponent implements View, BrickBaker {

    @Override
    public void render(Request request, Response response) {
        response.render(bakeBrick(request));
    }

    @Override
    public MustacheBrick bakeBrick(Request request) {
        return EmptyBrick.emptyBrick();
    }
}
