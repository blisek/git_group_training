package com.capgemini.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.RoundingMode;

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
	public void testDivisionModeCeiling() {

		// given
		int x = 7;
		int y = 4;
		int result = 2;
		// when,then
		assertEquals(result, MathematicalOperations.divisionWithRounding(x, y, RoundingMode.CEILING));

	}

	@Test
	public void testDivisionModeDown() {

		// given
		int x = 7;
		int y = 4;
		int result = 1;
		// when,then
		assertEquals(result, MathematicalOperations.divisionWithRounding(x, y, RoundingMode.DOWN));

	}

}
