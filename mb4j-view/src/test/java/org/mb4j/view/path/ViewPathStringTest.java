package org.mb4j.view.path;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mb4j.view.path.ViewPathString.pathStringOf;
import static org.mb4j.view.path.ViewPathString.viewPath;

public class ViewPathStringTest {
  @Test
  public void viewPath_splits_into_segments_at_slashes() {
    assertThat(viewPath("a").segments(), contains("a"));
    assertThat(viewPath("a/b").segments(), contains("a", "b"));
    assertThat(viewPath("a/b/c").segments(), contains("a", "b", "c"));
  }

  @Test
  public void viewPath_ignores_leading_and_trailing_slashes() {
    assertThat(viewPath("///a///").segments(), contains("a"));
    assertThat(viewPath("///a/b//").segments(), contains("a", "b"));
    assertThat(viewPath("////a/b/c/").segments(), contains("a", "b", "c"));
  }

  @Test
  public void viewPath_omits_empty_segments() {
    assertThat(viewPath("a//b").segments(), contains("a", "b"));
    assertThat(viewPath("a///b").segments(), contains("a", "b"));
    assertThat(viewPath("a//b//c").segments(), contains("a", "b", "c"));
  }

  @Test
  public void viewPath_null_string_or_empty_string_becomes_empty_path() {
    assertThat(viewPath(null).segments(), emptyIterable());
    assertThat(viewPath("").segments(), emptyIterable());
    assertThat(viewPath("/").segments(), emptyIterable());
    assertThat(viewPath("//").segments(), emptyIterable());
    assertThat(viewPath("///").segments(), emptyIterable());
  }

  @Test
  public void pathStringOf_concatenates_segments_using_slash() {
    assertThat(pathStringOf(viewPath("/")), is(""));
    assertThat(pathStringOf(viewPath("/a")), is("a"));
    assertThat(pathStringOf(viewPath("/a/b")), is("a/b"));
    assertThat(pathStringOf(viewPath("/a/b/c")), is("a/b/c"));
  }
}
