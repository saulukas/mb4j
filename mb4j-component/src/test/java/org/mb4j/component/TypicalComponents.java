package org.mb4j.component;

public class TypicalComponents {

    public static class BaseComponent extends ReflectiveComponent {

        @ResourceMethod
        public void resource1(Request request, Response response) {
        }

        @ResourceMethod
        public void resource2(Request request, Response response) {
        }
    }

    public static class ExtendedComponent extends BaseComponent {

        @ResourceMethod
        public void resource3(Request request, Response response) {
        }
    }

    public static class NonVoidResourceMethod extends ReflectiveComponent {

        @ResourceMethod
        public int resource3(Request request, Response response) {
            return 0;
        }
    }
}
