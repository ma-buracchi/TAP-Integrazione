package it.buracchi.ciphers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VigenereInputManagerIT {

	private Vigenere vigenere;
	private Parser parser;

	@Before
	public void setup() {
		parser = new InputManager();
	}

	@Test
	public void testKeyExtension() {
		vigenere = new Vigenere(parser, "test");
		vigenere.coding("testmessage");
		assertEquals("testtesttest", vigenere.getKey());
	}

	@Test
	public void testKeyWithSpecialCharacters() {
		vigenere = new Vigenere(parser, "t<e!s{t");
		vigenere.coding("testmessage");
		assertEquals("testtesttest", vigenere.getKey());
	}

	@Test
	public void testKeyWithUppercaseCharacters() {
		vigenere = new Vigenere(parser, "tESt");
		vigenere.coding("testmessage");
		assertEquals("testtesttest", vigenere.getKey());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyKey() {
		vigenere = new Vigenere(parser, "");
		vigenere.coding("testmessage");
	}

	@Test
	public void testCoding() {
		vigenere = new Vigenere(parser, "test");
		assertEquals("t", vigenere.coding("a"));
	}
	
	@Test
	public void testCodingLongerString() {
		vigenere = new Vigenere(parser, "test");
		assertEquals("mikmfikltkw", vigenere.coding("testmessage"));
	}

	@Test
	public void testCodingEmptyString() {
		vigenere = new Vigenere(parser, "test");
		assertEquals("", vigenere.coding(""));
	}

	@Test
	public void testDecoding() {
		vigenere = new Vigenere(parser, "test");
		assertEquals("a", vigenere.decoding("t"));
	}
	
	@Test
	public void testDecodingLongerString() {
		vigenere = new Vigenere(parser, "test");
		assertEquals("testmessage", vigenere.decoding("mikmfikltkw"));
	}

	@Test
	public void testDecodingEmptyString() {
		vigenere = new Vigenere(parser, "test");
		assertEquals("", vigenere.decoding(""));
	}

}
