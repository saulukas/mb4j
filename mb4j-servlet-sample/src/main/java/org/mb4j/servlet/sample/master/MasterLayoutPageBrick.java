package org.mb4j.servlet.sample.master;

import org.mb4j.brick.Brick;
import org.mb4j.controller.url.Url4Request;

public class MasterLayoutPageBrick extends Brick {
  Brick header;
  Brick content;
  Brick footer;
  String title = "Events";
  Url4Request dummy_js;
}
