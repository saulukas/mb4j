package org.mb4j.servlet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mb4j.controller.http.UrlPathStringToHome;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.mapping.ControllerMounter;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.url.UrlParams;
import static org.mb4j.controller.url.UrlPathString.urlPathOf;
import org.mb4j.servlet.TypicalViews.DocumentEdit;
import org.mb4j.servlet.TypicalViews.DocumentNew;
import org.mb4j.servlet.TypicalViews.Home;

public class ServletViewUrlStringResolverTest {
  @Test
  public void resolves_url_string_for_a_ViewUrl_taking_into_account_current_path2home() {
    String path2home = UrlPathStringToHome.from("path/from/home");
    ControllerMounter mounter = ControllerMounter
        .withHomeController(TypicalViews.HOME)
        .mount(urlPathOf("document/*"), TypicalViews.DOCUMENT)
        .mount(urlPathOf("document/new"), TypicalViews.DOCUMENT_NEW)
        .mount(urlPathOf("document/edit/*"), TypicalViews.DOCUMENT_EDIT);
    ServletViewUrlStringResolver resolver = new ServletViewUrlStringResolver(
        path2home,
        new ControllerMappings(mounter).controllerClass2UrlPathResolver());
    assertThat(resolver.resolve(ControllerUrl.of(Home.class)).toString(), is("../../"));
    assertThat(resolver.resolve(ControllerUrl.of(DocumentNew.class)).toString(), is("../../document/new"));
    assertThat(resolver.resolve(ControllerUrl.of(DocumentEdit.class)).toString(), is("../../document/edit"));
    assertThat(resolver.resolve(ControllerUrl.of(DocumentNew.class, UrlParams.of(urlPathOf("1/2/3")))).toString(),
        is("../../document/new/1/2/3"));
    assertThat(resolver.resolve(ControllerUrl.of(DocumentEdit.class, UrlParams.of(urlPathOf("a/b/c")))).toString(),
        is("../../document/edit/a/b/c"));
    NamedParams namedParams = NamedParams.empty()
        .withReplacedParam("order", "ascending")
        .withReplacedParam("removeDuplicates", "")
        .withReplacedParam("lt_message", "vidur prūdo bliūdas plūdo")
        .withReplacedParam("name with spaces", "someValue");
    System.out.println("" + namedParams);
    ControllerUrl url = ControllerUrl.of(DocumentEdit.class, UrlParams.of(urlPathOf("127"), namedParams));
    assertThat(resolver.resolve(url).toString(), is("../../document/edit"
        + "/127"
        + "?lt_message=vidur%20pr%C5%ABdo%20bli%C5%ABdas%20pl%C5%ABdo"
        + "&name%20with%20spaces=someValue"
        + "&order=ascending"
        + "&removeDuplicates="));
  }
}
