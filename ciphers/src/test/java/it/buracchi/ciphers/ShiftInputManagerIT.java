package it.buracchi.ciphers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShiftInputManagerIT {

	private Shift shift;
	private Parser parser;
	
	@Before
	public void setup() {
		parser = new InputManager();
		shift = new Shift(parser);
	}
	
	@Test
	public void testShiftCodingZeroPosition() {
		assertEquals("test", shift.coding("test", 0));
	}

	@Test
	public void testShiftCodingOnePosition() {
		assertEquals("uftu", shift.coding("test", 1));
	}

	@Test
	public void testShiftCodingTwentySixPosition() {
		assertEquals("test", shift.coding("test", 26));
	}

	@Test
	public void testShiftCodingTwentySevenPosition() {
		assertEquals("uftu", shift.coding("test", 27));
	}

	@Test
	public void testShiftCodingNegativePosition() {
		assertEquals("test", shift.coding("uftu", -1));
	}
	
	@Test
	public void testShiftCodingEmptyString() {
		assertEquals("", shift.coding("", 1));
	}
	
	@Test
	public void testShiftDecodingZeroPosition() {
		assertEquals("test", shift.decoding("test", 0));
	}
	
	@Test
	public void testShiftDecodingOnePosition() {
		assertEquals("test", shift.decoding("uftu", 1));
	}
	
	@Test
	public void testShiftDecodingTwentySixPosition() {
		assertEquals("test", shift.decoding("test", 26));
	}
	
	@Test
	public void testShiftDecodingTwentySevenPosition() {
		assertEquals("test", shift.decoding("uftu", 27));
	}
	
	@Test
	public void testShiftDecodingNegativePosition() {
		assertEquals("test", shift.decoding("sdrs", -1));
	}
	
	@Test
	public void testShiftDecodingEmptyString() {
		assertEquals("", shift.coding("", 1));
	}

}
