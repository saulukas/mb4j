package org.mb4j.component;

public class TypicalViews {

    public static class Home implements View {

        @Override
        public void render(Request request, Response response) {
        }
    }

    public static class Tutorial implements View {

        @Override
        public void render(Request request, Response response) {
        }
    }

    public static class TutorialOnEvents implements View {

        @Override
        public void render(Request request, Response response) {
        }
    }

    public static class TutorialOnSockets implements View {

        @Override
        public void render(Request request, Response response) {
        }
    }

    public static class TutorialTopic implements View {

        @Override
        public void render(Request request, Response response) {
        }
    }

    public static class TutorialOtherStuff implements View {

        @Override
        public void render(Request request, Response response) {
        }
    }
    public static final View HOME = new Home();
    public static final View TUTORIAL = new Tutorial();
    public static final View TUTORIAL_ON_EVENTS = new TutorialOnEvents();
    public static final View TUTORIAL_ON_SOCKETS = new TutorialOnSockets();
    public static final View TUTORIAL_TOPIC = new TutorialTopic();
    public static final View TUTORIAL_OTHER_STUFF = new TutorialOtherStuff();
}
