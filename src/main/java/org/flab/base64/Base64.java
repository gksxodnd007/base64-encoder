package org.flab.base64;

import java.nio.charset.Charset;

/**
 * @author taewoong.han
 * @since 2021.03.21
 */
public interface Base64 {

	String encode(String source, Charset charset);
	String decode(String source, Charset charset);
}
