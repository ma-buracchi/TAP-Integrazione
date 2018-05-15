package it.buracchi.ciphers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class OneTimePadTest {

	private OneTimePad otp;
	private Mapper<Character, String> converter;
	private Parser parser;
	private String[] huffman = { "0100", "011111", "11110", "01010", "000", "10110", "011100", "1101", "0010",
			"101111100", "1011110", "01011", "10100", "0011", "0110", "011110", "101111110", "1110", "1100", "100",
			"11111", "101110", "10101", "101111101", "011101", "101111111" };
	private String string_a_BinaryRepresentation = "0100";
	private String string_t_BinaryRepresentation = "100";
	private String string_e_BinaryRepresentation = "000";
	private String string_s_BinaryRepresentation = "1100";
	private String string_test_BinaryRepresentation = "1000001100100";
	private String shortKey = "0001";
	private String longKey = "0001011100101";
	private String string_a_CodedWithShortKey = "0101";
	private String string_test_CodedWithLongKey = "1001010000001";

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		parser = mock(Parser.class);
		converter = mock(Mapper.class);
		otp = new OneTimePad(parser, converter, huffman);
	}

	@Test
	public void testCreatedKeyOneCharachterLength() {
		when(parser.process(string_a_BinaryRepresentation)).thenReturn(string_a_BinaryRepresentation);
		assertEquals(string_a_BinaryRepresentation.length(), otp.createKey(string_a_BinaryRepresentation).length());
	}

	@Test
	public void testCreatedLongerThanKeyOneCharachterLength() {
		when(parser.process(string_test_BinaryRepresentation)).thenReturn(string_test_BinaryRepresentation);
		assertEquals(string_test_BinaryRepresentation.length(),
				otp.createKey(string_test_BinaryRepresentation).length());
	}

	@Test
	public void testFromStringToBinary() {
		when(parser.process("a")).thenReturn("a");
		when(converter.getValueFromKey('a')).thenReturn(string_a_BinaryRepresentation);
		assertEquals(string_a_BinaryRepresentation, otp.fromStringToBinary("a"));
	}

	@Test
	public void testFromStringToBinaryLongerString() {
		when(parser.process("test")).thenReturn("test");
		when(converter.getValueFromKey('t')).thenReturn(string_t_BinaryRepresentation);
		when(converter.getValueFromKey('e')).thenReturn(string_e_BinaryRepresentation);
		when(converter.getValueFromKey('s')).thenReturn(string_s_BinaryRepresentation);
		assertEquals(string_test_BinaryRepresentation, otp.fromStringToBinary("test"));
	}

	@Test
	public void testFromBinaryToString() {
		when(converter.containsValue(string_a_BinaryRepresentation)).thenReturn(true);
		when(converter.getKeyFromValue(string_a_BinaryRepresentation)).thenReturn('a');
		assertEquals("a", otp.fromBinaryToString(string_a_BinaryRepresentation));
	}

	@Test
	public void testFromBinaryToStringLongerString() {
		when(converter.containsValue(string_t_BinaryRepresentation)).thenReturn(true);
		when(converter.containsValue(string_e_BinaryRepresentation)).thenReturn(true);
		when(converter.containsValue(string_s_BinaryRepresentation)).thenReturn(true);
		when(converter.getKeyFromValue(string_t_BinaryRepresentation)).thenReturn('t');
		when(converter.getKeyFromValue(string_e_BinaryRepresentation)).thenReturn('e');
		when(converter.getKeyFromValue(string_s_BinaryRepresentation)).thenReturn('s');
		assertEquals("test", otp.fromBinaryToString(string_test_BinaryRepresentation));
	}

	@Test
	public void testCoding() {
		when(parser.checkKey(shortKey, 4)).thenReturn(shortKey);
		assertEquals(string_a_CodedWithShortKey, otp.coding(string_a_BinaryRepresentation, shortKey));
	}

	@Test
	public void testCodingLongerString() {
		when(parser.checkKey(longKey, 13)).thenReturn(longKey);
		assertEquals(string_test_CodedWithLongKey, otp.coding(string_test_BinaryRepresentation, longKey));
	}

	@Test
	public void testDecoding() {
		when(parser.checkKey(shortKey, 4)).thenReturn(shortKey);
		assertEquals(string_a_BinaryRepresentation, otp.coding(string_a_CodedWithShortKey, shortKey));
	}

	@Test
	public void testDecodingLongerString() {
		when(parser.checkKey(longKey, 13)).thenReturn(longKey);
		assertEquals(string_test_BinaryRepresentation, otp.coding(string_test_CodedWithLongKey, longKey));
	}

}
