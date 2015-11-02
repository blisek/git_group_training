package com.capgemini.math;

import java.math.RoundingMode;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class DivisionWithRoundingTest {

	@Test
	public void testCeiling() {
		int result = DivisionWithRounding.divideWithRounding(7, 4, RoundingMode.CEILING);

		Assertions.assertThat(result).isEqualTo(2);
	}

	@Test
	public void testDown() {
		int result = DivisionWithRounding.divideWithRounding(7, 4, RoundingMode.DOWN);

		Assertions.assertThat(result).isEqualTo(1);
	}

}
