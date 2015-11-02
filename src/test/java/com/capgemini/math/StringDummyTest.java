package com.capgemini.math;

import static org.junit.Assert.*;

import org.fest.assertions.Assert;
import org.fest.assertions.Assertions;
import org.junit.Test;

public class StringDummyTest {

	@Test
	public void testStringDummy() {
		// given
		String a = "maleKotki";
		String b = "maleKoty";
		// when
		StringDummy sDummy = new StringDummy();
		// then
		Assertions.assertThat(sDummy.compareIfStringsHaveTheSameLengthsInDifferentMethods(a, b)).isNotEqualTo(0);
	}

}
