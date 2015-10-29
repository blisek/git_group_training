package com.capgemini.math;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class LogarithmTest {

	@Test(expected = NumberFormatException.class)
	public void log10CannotConvertStringTest() {
		String value = "abc";
		Logarithm.calculateBase10Logarithm(value);
	}
	
	@Test
	public void log10PowerOf10ArgumentTest() {
		String value = "100";
		double result = Logarithm.calculateBase10Logarithm(value);
		Assertions.assertThat(result).isEqualTo(2);
	}

	@Test
	public void log10LessThan0ArgumentTest() {
		String value = "-5";
		double result = Logarithm.calculateBase10Logarithm(value);
		Assertions.assertThat(result).isEqualTo(Double.NaN);
	}

	@Test
	public void log10NaNArgumentTest() {
		String value = "0";
		double result = Logarithm.calculateBase10Logarithm(value);
		Assertions.assertThat(result).isEqualTo(Double.NEGATIVE_INFINITY);
	}
}
