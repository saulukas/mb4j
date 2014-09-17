package org.mb4j.example.servlet.master;

import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.Request;
import org.mb4j.example.servlet.offer.PersonalOfferPanel;
import org.mb4j.servlet.Page;

@Singleton
public abstract class MasterLayoutPage extends Page {

    final PersonalOfferPanel headerPanel = PersonalOfferPanel.INSTANCE;
    final FooterPanel footerPanel = FooterPanel.INSTANCE;

    @Override
    public MustacheBrick bakeBrick(Request request) {
        MasterLayoutPageBrick brick = new MasterLayoutPageBrick();
        brick.header = headerPanel.bakeBrick(request);
        brick.content = bakeContentBrick(request);
        brick.footer = footerPanel.bakeBrick(request);
        brick.jquery_js = request.assetUrl("js/jquery.js");
        return brick;
    }

    protected abstract MustacheBrick bakeContentBrick(Request request);
}
