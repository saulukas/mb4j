package org.mb4j.component.utils;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mb4j.component.url.UrlPathStringToHome;

public class UrlPathStringToHomeTest {

    @Test
    public void returns_relative_path_to_home() {
        assertThat(UrlPathStringToHome.from(""), is(""));
        assertThat(UrlPathStringToHome.from("/"), is(""));
        assertThat(UrlPathStringToHome.from("a"), is(""));
        assertThat(UrlPathStringToHome.from("a/b"), is("../"));
        assertThat(UrlPathStringToHome.from("a/b/c"), is("../../"));
        assertThat(UrlPathStringToHome.from("a///b//c"), is("../../../../../"));
    }
}
