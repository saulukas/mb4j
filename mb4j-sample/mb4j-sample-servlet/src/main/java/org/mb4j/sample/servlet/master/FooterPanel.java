package org.mb4j.sample.servlet.master;

import com.google.inject.Singleton;
import org.mb4j.brick.Brick;
import org.mb4j.controller.Request;
import org.mb4j.controller.page.Panel;

@Singleton
public class FooterPanel extends Panel {
  Brick bakeBrickFrom(Request request) {
    FooterPanelBrick brick = new FooterPanelBrick();
    brick.timeUrl = request.resolve(TimeResource.url());
    return brick;
  }
}
