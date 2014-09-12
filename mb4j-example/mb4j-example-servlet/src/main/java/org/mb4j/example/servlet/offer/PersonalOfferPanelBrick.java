package org.mb4j.example.servlet.offer;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ControllerUrl4Response;

public class PersonalOfferPanelBrick extends MustacheBrick {

    boolean offerVisible = false;
    String offerText;
    String offerLinkText;
    ControllerUrl4Response toggleOfferUrl;

}
