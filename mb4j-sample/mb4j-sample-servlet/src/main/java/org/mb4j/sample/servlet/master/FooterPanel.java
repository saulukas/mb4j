package org.mb4j.sample.servlet.master;

import org.mb4j.sample.servlet.services.TimeService;
import com.google.inject.Singleton;
import org.mb4j.brick.Brick;
import org.mb4j.controller.Request;
import org.mb4j.controller.page.Panel;

@Singleton
public class FooterPanel extends Panel {
  Brick bakeBrickFrom(Request request) {
    FooterPanelBrick brick = new FooterPanelBrick();
    brick.timeUrl = request.resolve(TimeService.url());
    return brick;
  }
}
