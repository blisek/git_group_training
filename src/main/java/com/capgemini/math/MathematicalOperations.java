package com.capgemini.math;

import java.math.RoundingMode;

import org.apache.log4j.Logger;

import com.google.common.math.IntMath;

public class MathematicalOperations {

	final static Logger logger = Logger.getLogger(MathematicalOperations.class);

	public static long abs(int value) {
		long castValue = value;
		return value < 0 ? -castValue : castValue;
	}

	public static int divisionWithRounding(int value1, int value2, RoundingMode mode) {

		return IntMath.divide(value1, value2, mode);
	}

	public static boolean parityNumber(int value) {
		return value % 2 == 0 ? true : false;
	}

	public static long exponentiation(int value) {
		long result = (long) Math.pow(value, 2);

		logger.info("Result of exponentiation of " + value + " is: " + result);
		return result;

	}

	public static double base10Logarithm(double value) {
		return Math.log10(value);
	}
}
