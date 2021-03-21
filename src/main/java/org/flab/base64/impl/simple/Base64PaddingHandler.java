package org.flab.base64.impl.simple;

/**
 * @author taewoong.han
 * @since 2021.03.21
 */
class Base64PaddingHandler {

	public static String appendPadding(String source) {
		StringBuilder builder = new StringBuilder(source);
		int needPaddingCharacterNumber = calculateNeedPaddingCharacterNumber(source);
		builder.append("=".repeat(needPaddingCharacterNumber)); //jvm11에서 제공하는 api 사용. repeat
		return builder.toString();
	}

	public static int calculateNeedPaddingCharacterNumber(String source) {
		String binary = BinaryConverter.toBinary(source);
		int rest = binary.length() % 6;

		if (rest == 0) {
			return 0;
		}

		return (rest == 2) ? 2 : 1;
	}

	public static int countPaddingCharacter(String source) {
		if (source.endsWith("=")) {
			return source.substring(source.length() - 2, source.length() - 1).equals("=") ? 2 : 1;
		}

		return 0;
	}
}
