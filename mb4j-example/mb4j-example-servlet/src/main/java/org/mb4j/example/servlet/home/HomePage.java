package org.mb4j.example.servlet.home;

import com.google.inject.Inject;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ViewLocator;
import org.mb4j.component.Request;
import org.mb4j.example.servlet.master.MasterLayoutPage;

public class HomePage extends MasterLayoutPage {

    @Inject
    HomeContentPanel contentPanel;

    public static ViewLocator url() {
        return ViewLocator.of(HomePage.class);
    }

    @Override
    protected MustacheBrick bakeContentBrick(Request request) {
        return contentPanel.bakeBrick(request);
    }
}
