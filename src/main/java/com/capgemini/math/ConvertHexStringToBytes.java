package com.capgemini.math;

import org.uncommons.maths.binary.BinaryUtils;

public class ConvertHexStringToBytes {

	public byte[] convert(String hex) throws ConvertHexStringToBytesException {
		byte[] tabBytes = new byte[hex.length() / 2];

		if (MathematicalOperations.parityNumber(hex.length())) {

			if (testHex(hex)) {
				tabBytes = BinaryUtils.convertHexStringToBytes(hex);
			} else {
				throw new ConvertHexStringToBytesException("String must be hex");
			}
		} else {
			throw new ConvertHexStringToBytesException("Length string must be even");
		}
		return tabBytes;
	}

	private boolean testHex(String value) {
		boolean isHex;
		try {
			Integer.parseInt(value, 16);
			isHex = true;
		} catch (NumberFormatException e) {
			isHex = false;
		}
		return isHex;
	}
}
