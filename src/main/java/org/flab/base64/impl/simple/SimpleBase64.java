package org.flab.base64.impl.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.flab.base64.Base64;
import org.flab.base64.utils.IntegerUtils;

/**
 * @author taewoong.han
 * @since 2021.03.21
 */
public class SimpleBase64 implements Base64 {

    private static final int CUT_LENGTH = 6;

    private SimpleBase64() {
    }

    @Override
    public String encode(String source, Charset charset) {
        StringBuilder builder = new StringBuilder();
        String binary = BinaryConverter.toBinary(source, charset);
        BinaryCutter.cutWithPaddingZero(binary, CUT_LENGTH)
            .forEach(base64Binary -> builder.append(Base64Index.binaryKeyIndex.get(base64Binary)));

        builder.append("=".repeat(Base64PaddingHandler.calculateNeedPaddingCharacterNumber(source)));

        return builder.toString();
    }

    @Override
    public String decode(String source, Charset charset) {
        StringBuilder builder = new StringBuilder();

        Arrays.stream(source.split(""))
            .filter(str -> !str.equals("="))
            .map(Base64Index.characterKeyIndex::get)
            .forEach(builder::append);

        int paddingCount = Base64PaddingHandler.countPaddingCharacter(source);

        String binary = builder.substring(0, builder.length() - (paddingCount * 2));
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            int integer = Integer.parseInt(binary.substring(i, i + 8), 2);
            buffer.append(new String(IntegerUtils.intToByte(integer), charset));
        }

        return buffer.toString();
    }

    private static class LazyHolder {
        static final SimpleBase64 INSTANCE = new SimpleBase64();
    }

    public static SimpleBase64 getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class Base64Index {

        private static final Map<String, String> binaryKeyIndex = new HashMap<>();
        private static final Map<String, String> characterKeyIndex = new HashMap<>();

        static {
            String fileName = "base64-index.txt";
            InputStream inputStream = SimpleBase64.class
                .getClassLoader()
                .getResourceAsStream(fileName);

            if (inputStream == null) {
                throw new IllegalArgumentException(fileName + "is not found!!");
            }

            try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
                in.lines()
                    .forEach(line -> {
                        String[] splittedLine = line.split(",");
                        binaryKeyIndex.put(splittedLine[0], splittedLine[1]);
                        characterKeyIndex.put(splittedLine[1], splittedLine[0]);
                    });

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
