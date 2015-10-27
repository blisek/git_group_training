package com.capgemini.math;

import java.math.RoundingMode;

import com.google.common.math.IntMath;

public class MathematicalOperations {

	public static long abs(int value) {
		long castValue = value;
		return value < 0 ? -castValue : castValue;
	}

	public static int divisionWithRounding(int value1, int value2, RoundingMode mode) {

		return IntMath.divide(value1, value2, mode);

	}
}
