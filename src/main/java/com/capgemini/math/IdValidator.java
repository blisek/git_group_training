package com.capgemini.math;

import org.apache.log4j.Logger;

public class IdValidator {

	MathematicalOperations calculator = new MathematicalOperations();

	final static Logger logger = Logger.getLogger(IdValidator.class);

	public void validateId(int id) throws IllegalArgumentException {

		if (MathematicalOperations.parityNumber(id)) {
			logger.info("Id " + id + "is valid");
		} else {
			logger.info("Id " + id + "isn't valid");
			throw new IllegalArgumentException("Id should  be even number");
		}
	}
}
