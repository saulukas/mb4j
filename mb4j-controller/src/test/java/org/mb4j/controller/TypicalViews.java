package org.mb4j.controller;

import org.mb4j.controller.ViewResponse;
import org.mb4j.controller.View;
import org.mb4j.controller.ViewRequest;

public class TypicalViews {
  public static class Home implements View {
    public ViewResponse handle(ViewRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class Tutorial implements View {
    public ViewResponse handle(ViewRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class TutorialOnEvents implements View {
    public ViewResponse handle(ViewRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class TutorialOnSockets implements View {
    public ViewResponse handle(ViewRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class TutorialTopic implements View {
    public ViewResponse handle(ViewRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  public static class TutorialOtherStuff implements View {
    public ViewResponse handle(ViewRequest request) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }
  public static final View HOME = new Home();
  public static final View TUTORIAL = new Tutorial();
  public static final View TUTORIAL_ON_EVENTS = new TutorialOnEvents();
  public static final View TUTORIAL_ON_SOCKETS = new TutorialOnSockets();
  public static final View TUTORIAL_TOPIC = new TutorialTopic();
  public static final View TUTORIAL_OTHER_STUFF = new TutorialOtherStuff();
}
