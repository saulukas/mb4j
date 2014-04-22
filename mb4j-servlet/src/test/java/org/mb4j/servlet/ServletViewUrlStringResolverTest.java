package org.mb4j.servlet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mb4j.controller.NamedParams;
import org.mb4j.controller.ViewParams;
import static org.mb4j.controller.http.HttpPathToHome.pathStringToHomeFrom;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.mapping.ControllerMounter;
import static org.mb4j.controller.path.UrlPathString.urlPath;
import org.mb4j.controller.url.ViewUrl;
import org.mb4j.servlet.TypicalViews.DocumentEdit;
import org.mb4j.servlet.TypicalViews.DocumentNew;
import org.mb4j.servlet.TypicalViews.Home;

public class ServletViewUrlStringResolverTest {
  @Test
  public void resolves_url_string_for_a_ViewUrl_taking_into_account_current_path2home() {
    String path2home = pathStringToHomeFrom("path/from/home");
    ControllerMounter mounter = ControllerMounter
        .withHomeController(TypicalViews.HOME)
        .mount(urlPath("document/*"), TypicalViews.DOCUMENT)
        .mount(urlPath("document/new"), TypicalViews.DOCUMENT_NEW)
        .mount(urlPath("document/edit/*"), TypicalViews.DOCUMENT_EDIT);
    ServletViewUrlStringResolver resolver = new ServletViewUrlStringResolver(
        path2home,
        new ControllerMappings(mounter).controllerClass2UrlPathResolver());
    assertThat(resolver.urlStringOf(ViewUrl.of(Home.class)), is("../../"));
    assertThat(resolver.urlStringOf(ViewUrl.of(DocumentNew.class)), is("../../document/new"));
    assertThat(resolver.urlStringOf(ViewUrl.of(DocumentEdit.class)), is("../../document/edit"));
    assertThat(resolver.urlStringOf(ViewUrl.of(DocumentNew.class, ViewParams.of(urlPath("1/2/3")))),
        is("../../document/new/1/2/3"));
    assertThat(resolver.urlStringOf(ViewUrl.of(DocumentEdit.class, ViewParams.of(urlPath("a/b/c")))),
        is("../../document/edit/a/b/c"));
    NamedParams namedParams = NamedParams.empty()
        .withReplacedParam("order", "ascending")
        .withReplacedParam("removeDuplicates", "")
        .withReplacedParam("lt_message", "vidur prūdo bliūdas plūdo")
        .withReplacedParam("name with spaces", "someValue");
    System.out.println("" + namedParams);
    ViewUrl url = ViewUrl.of(DocumentEdit.class, ViewParams.of(urlPath("127"), namedParams));
    assertThat(resolver.urlStringOf(url), is("../../document/edit"
        + "/127"
        + "?lt_message=vidur%20pr%C5%ABdo%20bli%C5%ABdas%20pl%C5%ABdo"
        + "&name%20with%20spaces=someValue"
        + "&order=ascending"
        + "&removeDuplicates="));
  }
}
