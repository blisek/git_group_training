package com.capgemini.math;

import static org.junit.Assert.*;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MathematicalOperationsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAbs() {
		assertNotEquals(0, MathematicalOperations.abs(Integer.MIN_VALUE));
		assertNotEquals(Integer.MIN_VALUE, MathematicalOperations.abs(Integer.MIN_VALUE));
		for (int i = 0; i < 12; ++i) {
			int pow2 = (int) Math.pow(2, i);
			assertEquals(pow2, MathematicalOperations.abs(-pow2));
		}
	}

	@Test
	public void testNumberIsParity() {
		// given
		int number = 6;

		// when
		boolean result = MathematicalOperations.parityNumber(number);
		// then
		Assertions.assertThat(result).isTrue();
	}

	@Test
	public void testNumberIsNotParity() {
		// given
		int number = 5;

		// when
		boolean result = MathematicalOperations.parityNumber(number);
		// then
		Assertions.assertThat(result).isFalse();
	}

}
