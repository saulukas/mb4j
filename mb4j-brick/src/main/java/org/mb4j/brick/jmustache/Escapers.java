//
// JMustache - A Java implementation of the Mustache templating language
// http://github.com/samskivert/jmustache/blob/master/LICENSE
package org.mb4j.brick.jmustache;

import com.google.common.html.HtmlEscapers;

/**
 * Defines some standard {@link Mustache.Escaper}s.
 */
public class Escapers {

    /**
     * Escapes HTML entities.
     */
    public static final Mustache.Escaper HTML
            = new Mustache.Escaper() {
                com.google.common.escape.Escaper guavaEscaper = HtmlEscapers.htmlEscaper();

                @Override
                public String escape(String raw) {
                    return guavaEscaper.escape(raw);
                }
            };
//           = simple(new String[][] {
//        { "&",  "&amp;" },
//        { "'",  "&#39;" },
//        { "\"", "&quot;" },
//        { "<",  "&lt;" },
//        { ">",  "&gt;" }
//    });

    /**
     * An escaper that does no escaping.
     */
    public static final Mustache.Escaper NONE = new Mustache.Escaper() {
        @Override
        public String escape(String text) {
            return text;
        }
    };

    /**
     * Returns an escaper that replaces a list of text sequences with canned replacements.
     *
     * @param repls a list of {@code (text, replacement)} pairs.
     */
    public static Mustache.Escaper simple(final String[]... repls) {
        return new Mustache.Escaper() {
            @Override
            public String escape(String text) {
                for (String[] escape : repls) {
                    text = text.replace(escape[0], escape[1]);
                }
                return text;
            }
        };
    }
}
