package org.flab.base64.impl.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taewoong.han
 * @since 2021.03.21
 */
class BinaryCutter {

	public static List<String> cut(String binary, int cutLength) {
		List<String> stringList = new ArrayList<>();
		int endIdx = binary.length() - (binary.length() % cutLength);
		for (int i = 0; i < endIdx; i+=cutLength) {
			stringList.add(binary.substring(i, i + cutLength));
		}

		int rest = binary.length() % cutLength;

		if (rest == 0) {
			return stringList;
		}

		int startIdx = binary.length() / cutLength * cutLength;

		String lastCutBinary = binary.substring(startIdx, startIdx + rest)
			+ "0".repeat(cutLength - rest);

		stringList.add(lastCutBinary);
		return stringList;
	}
}
