package com.capgemini.math;

import java.math.RoundingMode;

import com.google.common.math.IntMath;

public class DivisionWithRounding {

	public static int divideWithRounding(int x, int y, RoundingMode mode) {

		return IntMath.divide(x, y, mode);
	}

}
