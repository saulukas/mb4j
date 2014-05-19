package org.mb4j.sample.liferay.util;

import java.util.Enumeration;
import javax.portlet.Portlet;
import javax.portlet.PortletRequest;

public class PortletDebugUtils {
    public static long printParametersOf(String action, Portlet portlet, PortletRequest req) {
        System.out.println("==== " + action + " ==== " + portlet.getClass().getSimpleName());
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            System.out.println("    " + paramName + "=[" + req.getParameter(paramName) + "]");
        }
        return System.nanoTime();
    }

    public static void printDeltaNanos(String action, Portlet portlet, long startNanos) {
        System.out.println("==== " + action + " ==== " + portlet.getClass().getSimpleName()
                + " " + deltaNanosString(startNanos));
    }

    public static String deltaNanosString(long startNanos) {
        long deltaNanos = System.nanoTime() - startNanos;
        long deltaMicros = deltaNanos / 1000;
        String result = Long.toString(deltaMicros);
        while (result.length() < 4) {
            result = "0" + result;
        }
        result = result.substring(0, result.length() - 3) + "."
                + result.substring(result.length() - 3);
        return "deltaMillis = " + result;
    }
}
