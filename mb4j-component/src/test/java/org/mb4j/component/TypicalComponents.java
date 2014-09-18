package org.mb4j.component;

import org.mb4j.component.form.ActionResponse;
import org.mb4j.component.url.NamedParams;

public class TypicalComponents {

    public static class BaseComponent extends ReflectiveComponent {

        @ResourceMethod
        public void resource1(Request request, Response response) {
        }

        @ResourceMethod
        public void resource2(Request request, Response response) {
        }

        @ActionMethod
        public ActionResponse action1(Request request, NamedParams formInput) {
            return null;
        }

        @ActionMethod
        public ActionResponse action2(Request request, NamedParams formInput) {
            return null;
        }
    }

    public static class ExtendedComponent extends BaseComponent {

        @ResourceMethod
        public void resource3(Request request, Response response) {
        }

        @ActionMethod
        public ActionResponse action3(Request request, NamedParams formInput) {
            return null;
        }
    }

    public static class ComponentWithInvalidResourceMethods extends ReflectiveComponent {

        @ResourceMethod
        public void validResourceMethod(Request request, Response response) {
        }

        @ResourceMethod
        public int nonVoidResourceMethod(Request request, Response response) {
            return 0;
        }

        @ResourceMethod
        public void wrongParameterOrder(Response response, Request request) {
        }
    }

    public static class ComponentWithInvalidActionMethods extends ReflectiveComponent {

        @ActionMethod
        public ActionResponse validActionMethod(Request request, NamedParams formInput) {
            return null;
        }

        @ActionMethod
        public void voidActionMethod(Request request, NamedParams formInput) {
        }

        @ActionMethod
        public ActionResponse wrongParameterOrder(NamedParams formInput, Request request) {
            return null;
        }
    }
}
