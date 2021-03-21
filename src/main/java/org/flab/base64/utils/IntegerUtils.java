package org.flab.base64.utils;

/**
 * @author taewoong.han
 * @since 2021.03.22
 */
public class IntegerUtils {

	public static byte[] intToByte(int value) {
		int byteSize = 1;

		if ((value & 0xFF000000) != 0) {
			byteSize = 4;
		} else if ((value & 0x00FF0000) != 0) {
			byteSize = 3;
		} else if ((value & 0x0000FF00) != 0) {
			byteSize = 2;
		}

		byte[] bytes=new byte[byteSize];

		switch (byteSize) {
			case 4:
				bytes[0]=(byte)((value&0xFF000000)>>24);
				bytes[1]=(byte)((value&0x00FF0000)>>16);
				bytes[2]=(byte)((value&0x0000FF00)>>8);
				bytes[3]=(byte) (value&0x000000FF);
				break;
			case 3:
				bytes[0]=(byte)((value&0x00FF0000)>>16);
				bytes[1]=(byte)((value&0x0000FF00)>>8);
				bytes[2]=(byte) (value&0x000000FF);
				break;
			case 2:
				bytes[0]=(byte)((value&0x0000FF00)>>8);
				bytes[1]=(byte) (value&0x000000FF);
				break;
			case 1:
				bytes[0]=(byte) (value&0x000000FF);
				break;
		}

		return bytes;
	}
}
