package org.flab.base64.impl.simple;

import java.util.stream.Stream;

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
public class Base64PaddingHandlerTest {

	@Test
	void paddingCount_whenBinarySizeIs16() {
		//given
		String source = "AB";

		//when
		int actual = Base64PaddingHandler.calculateNeedPaddingCharacterNumber(source);

		//then
		Assertions.assertEquals(1, actual);
	}

	@Test
	void paddingCount_whenBinarySizeIs8() {
		//given
		String source = "A";

		//when
		int actual = Base64PaddingHandler.calculateNeedPaddingCharacterNumber(source);

		//then
		Assertions.assertEquals(2, actual);
	}

	@Test
	void paddingCount_whenBinarySizeIs24() {
		//given
		String source = "ABC";

		//when
		int actual = Base64PaddingHandler.calculateNeedPaddingCharacterNumber(source);

		//then
		Assertions.assertEquals(0, actual);
	}

	@Test
	void appendPadding_whenBinarySizeIs8() {
		//given
		String source = "A";

		//when
		String actual = Base64PaddingHandler.appendPadding(source);

		//then
		Assertions.assertEquals("A==", actual);
	}

	@Test
	void appendPadding_whenBinarySizeIs16() {
		//given
		String source = "AB";

		//when
		String actual = Base64PaddingHandler.appendPadding(source);

		//then
		Assertions.assertEquals("AB=", actual);
	}

	@Test
	void appendPadding_whenBinarySizeIs24() {
		//given
		String source = "ABC";

		//when
		String actual = Base64PaddingHandler.appendPadding(source);

		//then
		Assertions.assertEquals("ABC", actual);
	}

	@ParameterizedTest
	@ArgumentsSource(CountPaddingTestInputProvider.class)
	void countPaddingTest(Tuple<String, Integer> argument) {
		//given
		String input = argument.input;

		//when
		int actual = Base64PaddingHandler.countPaddingCharacter(input);

		//then
		Assertions.assertEquals(argument.expect, actual);
	}

	static class CountPaddingTestInputProvider implements ArgumentsProvider {

		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
			return Stream.of(
				Arguments.of(new Tuple<>("argument==", 2)),
				Arguments.of(new Tuple<>("argument=", 1)),
				Arguments.of(new Tuple<>("argument", 0)),
				Arguments.of(new Tuple<>("argument=if", 0))
			);
		}
	}
}
