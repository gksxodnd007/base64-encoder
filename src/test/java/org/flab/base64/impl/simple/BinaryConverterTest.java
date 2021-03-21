package org.flab.base64.impl.simple;

import java.util.stream.Stream;

import org.flab.base64.support.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

/**
 * @author taewoong.han
 * @since 2021.03.21
 */
public class BinaryConverterTest {

	@ParameterizedTest
	@ArgumentsSource(CharacterBinaryConvertSpecProvider.class)
	void toBinary_whenInputIsCharacter(Tuple<Character, String> argument) {
		//given
		char source = argument.input;

		//when
		String actual = BinaryConverter.toBinary(source);

		//then
		Assertions.assertEquals(argument.expect, actual);
	}

	static class CharacterBinaryConvertSpecProvider implements ArgumentsProvider {

		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
			return Stream.of(
				Arguments.of(new Tuple<>('a', "01100001")),
				Arguments.of(new Tuple<>('b', "01100010")),
				Arguments.of(new Tuple<>('c', "01100011")),
				Arguments.of(new Tuple<>('A', "01000001")),
				Arguments.of(new Tuple<>('B', "01000010")),
				Arguments.of(new Tuple<>('C', "01000011")),
				Arguments.of(new Tuple<>('!', "00100001"))
			);
		}
	}

	@ParameterizedTest
	@ArgumentsSource(StringBinaryConvertSpecProvider.class)
	void toBinary_whenInputIsString(Tuple<String, String> argument) {
		//given
		String source = argument.input;

		//when
		String actual = BinaryConverter.toBinary(source);

		//then
		Assertions.assertEquals(argument.expect, actual);
	}

	static class StringBinaryConvertSpecProvider implements ArgumentsProvider {
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
			return Stream.of(
				Arguments.of(new Tuple<>("abc", "011000010110001001100011")),
				Arguments.of(new Tuple<>("ab!", "011000010110001000100001")),
				Arguments.of(new Tuple<>("AbC", "010000010110001001000011"))
			);
		}
	}
}
