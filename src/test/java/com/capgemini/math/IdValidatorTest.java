package com.capgemini.math;



import org.junit.Before;
import org.junit.Test;



public class IdValidatorTest {
	
	IdValidator validator;
	
	@Before
	public void init() {
		validator = new IdValidator();
	}

	@Test(expected = IllegalArgumentException.class)
	public void notEvenNumberThowsExceptionTest() {
		int id = 1233;
		validator.validateId(id);
	}
	
	@Test
	public void evenNumberTest() {
		int id = 1234;
		validator.validateId(id);
	}
	
}