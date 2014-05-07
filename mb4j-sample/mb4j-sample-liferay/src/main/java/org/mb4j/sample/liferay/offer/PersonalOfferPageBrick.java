package org.mb4j.sample.liferay.offer;

import org.mb4j.brick.Brick;
import org.mb4j.controller.url.ControllerUrl4Response;

public class PersonalOfferPageBrick extends Brick {
  boolean offerVisible = false;
  String offerText;
  String offerLinkText;
  ControllerUrl4Response toggleOfferUrl;
}
