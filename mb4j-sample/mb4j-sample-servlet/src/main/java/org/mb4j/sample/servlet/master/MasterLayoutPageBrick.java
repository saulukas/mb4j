package org.mb4j.sample.servlet.master;

import org.mb4j.brick.Brick;
import org.mb4j.controller.url.AssetUrl4Response;

public class MasterLayoutPageBrick extends Brick {
  Brick header;
  Brick content;
  Brick footer;
  String title = "Events";
  AssetUrl4Response jquery_js;
}
