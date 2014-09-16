package org.mb4j.example.servlet.offer;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ViewUrl4Response;

public class PersonalOfferPanelBrick extends MustacheBrick {

    boolean offerVisible = false;
    String offerText;
    String offerLinkText;
    ViewUrl4Response toggleOfferUrl;

}
