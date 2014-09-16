package org.mb4j.example.liferay.time;

import com.google.inject.Singleton;
import java.io.IOException;
import java.util.Date;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.Request;
import org.mb4j.component.Response;
import org.mb4j.liferay.PortletView;

@Singleton
public class TimeView extends PortletView {

    static class Brick extends MustacheBrick {

        String timeResourceUrl;
    }

    @Override
    public MustacheBrick bakeBrick(Request request) {
        Brick brick = new Brick();
        brick.timeResourceUrl = request.resolveResourceUrl(this, "time");
        return brick;
    }

    @ResourceMethod
    public void time(Request request, Response response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("" + new Date());
    }
}
