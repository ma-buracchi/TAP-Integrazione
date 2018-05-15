package it.buracchi.ciphers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OneTimePadInputManagerMapperIT {

	private OneTimePad otp;
	private Parser parser;
	private Mapper<Character, String> map;
	private String string_a_BinaryRepresentation = "0100";
	private String string_test_BinaryRepresentation = "1000001100100";
	private String shortKey = "0001";
	private String longKey = "0001011100101";
	private String string_a_CodedWithShortKey = "0101";
	private String string_test_CodedWithLongKey = "1001010000001";
	private String[] huffman = { "0100", "011111", "11110", "01010", "000", "10110", "011100", "1101", "0010",
			"101111100", "1011110", "01011", "10100", "0011", "0110", "011110", "101111110", "1110", "1100", "100",
			"11111", "101110", "10101", "101111101", "011101", "101111111" };

	@Before
	public void setup() {
		parser = new InputManager();
		map = new BiMapper<Character, String>();
		otp = new OneTimePad(parser, map, huffman);
	}

	@Test
	public void testCreatedKeyOneCharachterLength() {
		assertEquals(string_a_BinaryRepresentation.length(), otp.createKey(string_a_BinaryRepresentation).length());
	}

	@Test
	public void testCreatedLongerThanKeyOneCharachterLength() {
		assertEquals(string_test_BinaryRepresentation.length(),
				otp.createKey(string_test_BinaryRepresentation).length());
	}

	@Test
	public void testFromStringToBinary() {
		assertEquals(string_a_BinaryRepresentation, otp.fromStringToBinary("a"));
	}

	@Test
	public void testFromStringToBinaryLongerString() {
		assertEquals(string_test_BinaryRepresentation, otp.fromStringToBinary("test"));
	}

	@Test
	public void testFromBinaryToString() {
		assertEquals("a", otp.fromBinaryToString(string_a_BinaryRepresentation));
	}

	@Test
	public void testFromBinaryToStringLongerString() {
		assertEquals("test", otp.fromBinaryToString(string_test_BinaryRepresentation));
	}

	@Test
	public void testCoding() {
		assertEquals(string_a_CodedWithShortKey, otp.coding(string_a_BinaryRepresentation, shortKey));
	}

	@Test
	public void testCodingLongerString() {
		assertEquals(string_test_CodedWithLongKey, otp.coding(string_test_BinaryRepresentation, longKey));
	}

	@Test
	public void testDecoding() {
		assertEquals(string_a_BinaryRepresentation, otp.coding(string_a_CodedWithShortKey, shortKey));
	}

	@Test
	public void testDecodingLongerString() {
		assertEquals(string_test_BinaryRepresentation, otp.coding(string_test_CodedWithLongKey, longKey));
	}

}
