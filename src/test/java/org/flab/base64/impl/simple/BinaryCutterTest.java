package org.flab.base64.impl.simple;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author taewoong.han
 * @since 2021.03.22
 */
public class BinaryCutterTest {

	@Test
	void cutWithPaddingZero_whenBinaryLengthIs8() {
		//given
		String binary = "10010100";

		//when
		List<String> actual = BinaryCutter.cutWithPaddingZero(binary, 6);

		//then
		List<String> expect = new ArrayList<>();
		expect.add("100101");
		expect.add("000000");

		Assertions.assertArrayEquals(expect.toArray(), actual.toArray());
	}

	@Test
	void cutWithPaddingZero_whenBinaryLengthIs16() {
		//given
		String binary = "1001010010111100";

		//when
		List<String> actual = BinaryCutter.cutWithPaddingZero(binary, 6);

		//then
		List<String> expect = new ArrayList<>();
		expect.add("100101");
		expect.add("001011");
		expect.add("110000");

		Assertions.assertArrayEquals(expect.toArray(), actual.toArray());
	}

	@Test
	void cutWithPaddingZero_whenBinaryLengthIs24() {
		//given
		String binary = "100101001011110010111100";

		//when
		List<String> actual = BinaryCutter.cutWithPaddingZero(binary, 6);

		//then
		List<String> expect = new ArrayList<>();
		expect.add("100101");
		expect.add("001011");
		expect.add("110010");
		expect.add("111100");

		Assertions.assertArrayEquals(expect.toArray(), actual.toArray());
	}
}
