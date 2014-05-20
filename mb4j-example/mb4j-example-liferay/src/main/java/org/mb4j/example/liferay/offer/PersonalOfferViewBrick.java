package org.mb4j.example.liferay.offer;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.url.ControllerUrl4Response;

public class PersonalOfferViewBrick extends MustacheBrick {
  boolean offerVisible = false;
  String offerText;
  String offerLinkText;
  ControllerUrl4Response toggleOfferUrl;
}
