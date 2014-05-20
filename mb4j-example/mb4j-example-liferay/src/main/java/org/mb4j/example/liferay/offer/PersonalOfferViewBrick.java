package org.mb4j.example.liferay.offer;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.view.ViewUrl4Response;

public class PersonalOfferViewBrick extends MustacheBrick {
  boolean offerVisible = false;
  String offerText;
  String offerLinkText;
  ViewUrl4Response toggleOfferUrl;
}
