package org.mb4j.controller.http;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.mb4j.controller.url.NamedParams;

public class HttpNamedParams {
  public static NamedParams namedParametersFromRawQueryString(String rawQueryString) {
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
      query.append(name, params.valueOf(name));
    }
    return query.toString();
  }
}
