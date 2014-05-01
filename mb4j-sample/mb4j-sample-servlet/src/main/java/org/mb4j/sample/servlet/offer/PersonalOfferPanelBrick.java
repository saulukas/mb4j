package org.mb4j.sample.servlet.offer;

import org.mb4j.brick.Brick;
import org.mb4j.controller.url.ControllerUrl4Request;

public class PersonalOfferPanelBrick extends Brick {
  boolean offerVisible = false;
  String offerText;
  String offerLinkText;
  ControllerUrl4Request toggleOfferUrl;
}
