package it.buracchi.ciphers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SubInputManagerMapperIT {

	private Substitution sub;
	private Parser parser;
	private String newAlphabet;
	private Mapper<Character, Character> mapper;

	@Before
	public void setup() {
		parser = new InputManager();
		mapper = new BiMapper<Character, Character>();
		newAlphabet = "qwertyuiopasdfghjklzxcvbnm";
		sub = new Substitution(parser, mapper, newAlphabet);
	}

	@Test
	public void testCoding() {
		assertEquals("q", sub.coding("a"));
	}

	@Test
	public void testCodingLongerString() {
		assertEquals("ztlz", sub.coding("test"));
	}

	@Test
	public void testCodingEmptyString() {
		assertEquals("", sub.coding(""));
	}

	@Test
	public void testDecoding() {
		assertEquals("a", sub.decoding("q"));
	}

	@Test
	public void testDecodingLongerString() {
		assertEquals("test", sub.decoding("ztlz"));
	}

	@Test
	public void testDecodingEmptyString() {
		assertEquals("", sub.decoding(""));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewAlphabetShorterThenTwentySixLetters() {
		newAlphabet = "qwertyuiopasdfghj";
		sub = new Substitution(parser, mapper, newAlphabet);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewAlphabetLongerThenTwentySixLetters() {
		newAlphabet = "qwertyuiopasdfghjklzxcvbnmqwerty";
		sub = new Substitution(parser, mapper, newAlphabet);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewAlphabetWithIllegalCharacter() {
		newAlphabet = "qwertyuiopa?dfghjklzxcvbnm";
		sub = new Substitution(parser, mapper, newAlphabet);
	}

}
