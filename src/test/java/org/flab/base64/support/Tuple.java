package org.flab.base64.support;

/**
 * @author taewoong.han
 * @since 2021.03.21
 */
public class Tuple<I, E> {

	public final I input;
	public final E expect;

	public Tuple(I input, E expect) {
		this.input = input;
		this.expect = expect;
	}
}
