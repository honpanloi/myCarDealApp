package com.app.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidationTest {

	@Test
	void testIsValidUserName() {
		boolean isValid = false;
		String userName = "*&%!@$(^%";
		isValid = Validation.isValidUserName(userName);
		
		assertFalse(isValid);
		
		userName = "12345678";
		isValid = Validation.isValidUserName(userName);
		
		assertTrue(isValid);
	}

}
