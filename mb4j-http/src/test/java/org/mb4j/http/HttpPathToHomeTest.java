package org.mb4j.http;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.http.HttpPathToHome.pathStringToHomeFrom;

public class HttpPathToHomeTest {
  @Test
  public void returns_relative_path_to_home() {
    assertThat(pathStringToHomeFrom(""), is(""));
    assertThat(pathStringToHomeFrom("/"), is(""));
    assertThat(pathStringToHomeFrom("a"), is(""));
    assertThat(pathStringToHomeFrom("a/b"), is("../"));
    assertThat(pathStringToHomeFrom("a/b/c"), is("../../"));
    assertThat(pathStringToHomeFrom("a///b//c"), is("../../../../../"));
  }
}
