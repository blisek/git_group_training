package com.capgemini.math;

import org.uncommons.maths.binary.BinaryUtils;

public class ConvertHexStringToBytes {

	public byte[] convert(String hex) throws ConvertHexStringToBytesException {
		byte[] tabBytes = new byte[hex.length() / 2];

		if (MathematicalOperations.parityNumber(hex.length())) {

			if (testHex(hex)) {
				tabBytes = BinaryUtils.convertHexStringToBytes(hex);
			} else {
				throw new ConvertHexStringToBytesException("String must by hex");
			}
		} else {
			throw new ConvertHexStringToBytesException("Numbers chars in String must by even");
		}
		return tabBytes;
	}

	private boolean testHex(String value) {
		boolean ret;
		try {
			Integer.parseInt(value, 16);
			ret = true;
		} catch (NumberFormatException e) {
			ret = false;
		}
		return (ret);
	}
}
