package com.capgemini.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConvertHexStringToBytesTest {

	ConvertHexStringToBytes convert = new ConvertHexStringToBytes();

	@Test
	public void test_Hex_00() throws ConvertHexStringToBytesException {
		// given
		String hex = "00";
		byte[] expecteds = { 0 };
		// when
		byte[] actuals = convert.convert(hex);
		// then
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void test_Hex_01() throws ConvertHexStringToBytesException {
		// given
		String hex = "01";
		byte[] expecteds = { 1 };
		// when
		byte[] actuals = convert.convert(hex);
		// then
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void test_Hex_10() throws ConvertHexStringToBytesException {
		// given
		String hex = "10";
		byte[] expecteds = { 16 };
		// when
		byte[] actuals = convert.convert(hex);
		// then
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void test_Hex_99() throws ConvertHexStringToBytesException {
		// given
		String hex = "99";
		byte[] expecteds = { -103 };
		// when
		byte[] actuals = convert.convert(hex);
		// then
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void test_Hex_af() throws ConvertHexStringToBytesException {
		// given
		String hex = "af";
		byte[] expecteds = { -81 };
		// when
		byte[] actuals = convert.convert(hex);
		// then
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void test_Hex_9999() throws ConvertHexStringToBytesException {
		// given
		String hex = "9999";
		byte[] expecteds = { -103, -103 };
		// when
		byte[] actuals = convert.convert(hex);
		// then
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void test_Hex_ab23() throws ConvertHexStringToBytesException {
		// given
		String hex = "ab23";
		byte[] expecteds = { -85, 35 };
		// when
		byte[] actuals = convert.convert(hex);
		// then
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void test_Hex_afde() throws ConvertHexStringToBytesException {
		// given
		String hex = "afde";
		byte[] expecteds = { -81, -34 };
		// when
		byte[] actuals = convert.convert(hex);
		// then
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void test_Hex_5476afbc() throws ConvertHexStringToBytesException {
		// given
		String hex = "5476afbc";
		byte[] expecteds = { 84, 118, -81, -68 };
		// when
		byte[] actuals = convert.convert(hex);
		// then
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void test_Exception_with_no_even() {
		// given
		String hex = "123";
		String throwInfo = "Numbers chars in String must by even";
		String throwString = "";
		// when
		try {
			convert.convert(hex);
		} catch (ConvertHexStringToBytesException e) {
			throwString = e.getMessage();
		}
		assertEquals(throwInfo, throwString);
	}

	@Test
	public void test_Exception_String_not_hex() {
		// given
		String hex = "xx";
		String throwInfo = "String must by hex";
		String throwString = "";
		// when
		try {
			convert.convert(hex);
		} catch (ConvertHexStringToBytesException e) {
			throwString = e.getMessage();
		}
		assertEquals(throwInfo, throwString);
	}
}
