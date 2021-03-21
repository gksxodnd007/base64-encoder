package org.flab.base64.impl.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
