package org.mb4j.controller.utils;

import com.google.common.base.Strings;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.mb4j.controller.url.NamedParams;

public class HttpNamedParams {
  public static NamedParams namedParamsFromRawQuery(String rawQueryString) {
    if (Strings.isNullOrEmpty(rawQueryString)) {
      return NamedParams.empty();
    }
    UrlEncodedQueryString query = UrlEncodedQueryString.parse(rawQueryString);
    Map<String, String> name2value = new HashMap<>();
    Iterator<String> names = query.getNames();
    while (names.hasNext()) {
      String name = names.next();
      name2value.put(name, query.get(name));
    }
    NamedParams named = new NamedParams(name2value);
    return named;
  }

  public static String queryStringFrom(NamedParams params) {
    UrlEncodedQueryString query = UrlEncodedQueryString.create();
    for (String name : params.names()) {
      query.append(name, params.valueOrNullOf(name));
    }
    return query.toString();
  }
}
