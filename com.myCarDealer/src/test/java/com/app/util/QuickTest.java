package com.app.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class QuickTest {

	@Test
	void test() {
		System.out.println(Date.valueOf(LocalDate.now()));
	}

}
