package org.mb4j.servlet.sample.master;

import org.mb4j.brick.Brick;
import org.mb4j.controller.url.StaticUrl4Request;

public class MasterLayoutPageBrick extends Brick {
  Brick header;
  Brick content;
  Brick footer;
  String title = "Events";
  StaticUrl4Request dummy_js;
}
