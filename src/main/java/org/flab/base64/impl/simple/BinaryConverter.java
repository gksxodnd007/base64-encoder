package org.flab.base64.impl.simple;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author taewoong.han
 * @since 2021.03.21
 */
class BinaryConverter {

    public static String toBinary(String source) {
        return toBinary(source, StandardCharsets.UTF_8);
    }

    public static String toBinary(String source, Charset charset) {
        byte[] bytes = source.getBytes(charset);
        StringBuilder buffer = new StringBuilder();
        for (byte elem : bytes) {
            buffer.append(toBinary(elem));
        }

        return buffer.toString();
    }

    public static String toBinary(int source) {
        return padding(String.format("%8s", Integer.toBinaryString(source)));
    }

    private static String padding(String source) {
        return source.replace(" ", "0");
    }
}
