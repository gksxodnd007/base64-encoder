package org.flab.base64.impl.simple;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import org.flab.base64.Base64;
import org.flab.base64.support.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

/**
 * @author taewoong.han
 * @since 2021.03.21
 */
public class SimpleBase64Test {

	private final Base64 sut = SimpleBase64.getInstance();

	@ParameterizedTest
	@ArgumentsSource(Base64EncodingProvider.class)
	void encode_whenCharsetIsUTF8(Tuple<String, String> argument) {
		//given
		String string = argument.input;

		//when
		String actual = sut.encode(string, StandardCharsets.UTF_8);

		//then
		Assertions.assertEquals(argument.expect, actual);
	}

	static class Base64EncodingProvider implements ArgumentsProvider {

		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
			return Stream.of(
				Arguments.of(new Tuple<>("Many", "TWFueQ==")),
				Arguments.of(new Tuple<>("f-lab", "Zi1sYWI=")),
				Arguments.of(new Tuple<>("taewoong.han", "dGFld29vbmcuaGFu")),
				Arguments.of(new Tuple<>("base64@encodinga=3239", "YmFzZTY0QGVuY29kaW5nYT0zMjM5")),
				Arguments.of(new Tuple<>("f-lab.co.kr", "Zi1sYWIuY28ua3I="))
			);
		}
	}

	@ParameterizedTest
	@ArgumentsSource(Base64DecodingProvider.class)
	void decode_whenCharsetIsUTF8(Tuple<String, String> argument) {
		//given
		String string = argument.input;

		//when
		String actual = sut.decode(string, StandardCharsets.UTF_8);

		//then
		Assertions.assertEquals(argument.expect, actual);
	}

	static class Base64DecodingProvider implements ArgumentsProvider {

		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
			return Stream.of(
				Arguments.of(new Tuple<>("TWFueQ==", "Many")),
				Arguments.of(new Tuple<>("Zi1sYWI=", "f-lab")),
				Arguments.of(new Tuple<>("dGFld29vbmcuaGFu", "taewoong.han")),
				Arguments.of(new Tuple<>("YmFzZTY0QGVuY29kaW5nYT0zMjM5", "base64@encodinga=3239")),
				Arguments.of(new Tuple<>("Zi1sYWIuY28ua3I=", "f-lab.co.kr"))
			);
		}
	}
}
