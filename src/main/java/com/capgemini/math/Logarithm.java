package com.capgemini.math;

import org.apache.commons.lang3.math.NumberUtils;

public class Logarithm {
	public static double calculateBase10Logarithm(String value) {
		return MathematicalOperations.base10Logarithm(NumberUtils.createDouble(value));
	}
}
