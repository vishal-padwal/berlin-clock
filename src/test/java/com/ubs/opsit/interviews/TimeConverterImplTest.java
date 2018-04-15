package com.ubs.opsit.interviews;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TimeConverterImplTest {

	TimeConverter converter = new TimeConverterImpl();

	@Test(expected = NumberFormatException.class)
	public void testNumberFormatException() {
		converter.convertTime("14:sdddad:1234");
		converter.convertTime("14.1:sdddad:1234");
		converter.convertTime("14:31.1:1234");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentException() {
		converter.convertTime("60:30:30");
		converter.convertTime("14:89:30");
		converter.convertTime("14:30:67");
	}

	@Test(expected = NullPointerException.class)
	public void testNullPointerException() {
		converter.convertTime(null);
	}

	@Test
	public void testCorrectData() {
		String expected = "Y-RROO-RRRR-YYRYYROOOOO-OOOO";
		String output = converter.convertTime("14:30:30");

		assertEquals(expected, output);

		expected = "O-RRRR-RRRO-YYRYYRYYRYY-YYYY";
		output = converter.convertTime("23:59:59");

		assertEquals(expected, output);
	}

	@Test
	public void testSecondsBlink() {
		String expected = "Y-RROO-RRRR-YYRYYROOOOO-OOOO";
		String output = converter.convertTime("14:30:30");
		assertEquals(expected, output);

		expected = "O-RROO-RRRR-YYRYYROOOOO-OOOO";
		output = converter.convertTime("14:30:31");
		assertEquals(expected, output);
	}

	@Test
	public void testSecondRowHoursNumberOfLamps() {
		String expected = converter.convertTime("14:30:30");
		assertEquals(4, (expected.split("-"))[1].length());
	}

	@Test
	public void testThirdRowHoursNumberOfLamps() {
		String expected = converter.convertTime("14:30:30");
		assertEquals(4, (expected.split("-"))[2].length());
	}

	@Test
	public void testFourthRowMinutesNumberOfLamps() {
		String expected = converter.convertTime("14:30:30");
		assertEquals(11, (expected.split("-"))[3].length());
	}

	@Test
	public void testFifthRowMinutesNumberOfLamps() {
		String expected = converter.convertTime("14:30:30");
		assertEquals(4, (expected.split("-"))[4].length());
	}

	@Test
	public void testNumberOfRowsInOutput() {
		String expected = converter.convertTime("14:30:30");
		assertEquals(5, (expected.split("-")).length);
	}
}
