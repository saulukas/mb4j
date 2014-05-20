package org.mb4j.component;

import org.mb4j.component.view.ViewResponse;
import org.mb4j.component.view.View;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.page.Page;

public class TypicalPages {
  public static class Home extends Page {
    @Override
    public void handle(ViewRequest request, ViewResponse response) {
    }
  }

  public static class Tutorial extends Page {
    @Override
    public void handle(ViewRequest request, ViewResponse response) {
    }
  }

  public static class TutorialOnEvents extends Page {
    @Override
    public void handle(ViewRequest request, ViewResponse response) {
    }
  }

  public static class TutorialOnSockets extends Page {
    @Override
    public void handle(ViewRequest request, ViewResponse response) {
    }
  }

  public static class TutorialTopic extends Page {
    @Override
    public void handle(ViewRequest request, ViewResponse response) {
    }
  }

  public static class TutorialOtherStuff extends Page {
    @Override
    public void handle(ViewRequest request, ViewResponse response) {
    }
  }
  public static final View HOME = new Home();
  public static final View TUTORIAL = new Tutorial();
  public static final View TUTORIAL_ON_EVENTS = new TutorialOnEvents();
  public static final View TUTORIAL_ON_SOCKETS = new TutorialOnSockets();
  public static final View TUTORIAL_TOPIC = new TutorialTopic();
  public static final View TUTORIAL_OTHER_STUFF = new TutorialOtherStuff();
}
