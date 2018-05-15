package it.buracchi.ciphers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class ShiftTest {

	private Shift shift;
	private Parser parser;
	
	@Before
	public void setup() {
		parser = mock(Parser.class);
		shift = new Shift(parser);
	}
	
	@Test
	public void testShiftCodingZeroPosition() {
		when(parser.process("test")).thenReturn("test");
		assertEquals("test", shift.coding("test", 0));
	}

	@Test
	public void testShiftCodingOnePosition() {
		when(parser.process("test")).thenReturn("test");
		assertEquals("uftu", shift.coding("test", 1));
	}

	@Test
	public void testShiftCodingTwentySixPosition() {
		when(parser.process("test")).thenReturn("test");
		assertEquals("test", shift.coding("test", 26));
	}

	@Test
	public void testShiftCodingTwentySevenPosition() {
		when(parser.process("test")).thenReturn("test");
		assertEquals("uftu", shift.coding("test", 27));
	}

	@Test
	public void testShiftCodingNegativePosition() {
		when(parser.process("uftu")).thenReturn("uftu");
		assertEquals("test", shift.coding("uftu", -1));
	}
	
	@Test
	public void testShiftCodingEmptyString() {
		when(parser.process("")).thenReturn("");
		assertEquals("", shift.coding("", 1));
	}
	
	@Test
	public void testShiftDecodingZeroPosition() {
		when(parser.process("test")).thenReturn("test");
		assertEquals("test", shift.decoding("test", 0));
	}
	
	@Test
	public void testShiftDecodingOnePosition() {
		when(parser.process("uftu")).thenReturn("uftu");
		assertEquals("test", shift.decoding("uftu", 1));
	}
	
	@Test
	public void testShiftDecodingTwentySixPosition() {
		when(parser.process("test")).thenReturn("test");
		assertEquals("test", shift.decoding("test", 26));
	}
	
	@Test
	public void testShiftDecodingTwentySevenPosition() {
		when(parser.process("uftu")).thenReturn("uftu");
		assertEquals("test", shift.decoding("uftu", 27));
	}
	
	@Test
	public void testShiftDecodingNegativePosition() {
		when(parser.process("sdrs")).thenReturn("sdrs");
		assertEquals("test", shift.decoding("sdrs", -1));
	}
	
	@Test
	public void testShiftDecodingEmptyString() {
		when(parser.process("")).thenReturn("");
		assertEquals("", shift.coding("", 1));
	}
	
}
