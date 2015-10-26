package com.capgemini.math;

public class MathematicalOperations {

	public static long abs(int value) {
		long castValue = value;
		return value < 0 ? -castValue : castValue;
	}

	public static boolean parityNumber(int value) {
		return value % 2 == 0 ? true : false;
	}
}
