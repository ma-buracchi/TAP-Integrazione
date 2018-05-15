package it.buracchi.ciphers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InputManagerTest {

	private InputManager im;
	private String test;

	@Before
	public void setup() {
		im = new InputManager();
	}

	@Test
	public void testInputOk() {
		test = "test";
		assertEquals("test", im.process(test));
	}

	@Test
	public void testIllegalCharacterInput() {
		test = "te:st";
		assertEquals("test", im.process(test));
	}

	@Test
	public void testInputWithSpaces() {
		test = "te st ";
		assertEquals("test", im.process(test));
	}

	@Test
	public void testInputWithUppercase() {
		test = "tEsT";
		assertEquals("test", im.process(test));
	}

	@Test
	public void testEmptyInput() {
		test = "";
		assertEquals("", im.process(test));
	}

	@Test
	public void testCheckAlphabet() {
		test = "qwertyuiopasdfghjklzxcvbnm";
		assertEquals(test, im.checkAlphabet(test));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckAlphabetLongerThenTwentysixCharacters() {
		test = "qwertyuiopasdfghjklzxcvbnmq";
		im.checkAlphabet(test);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckAlphabetShorterThenTwentysixCharacters() {
		test = "qwertyuiopasdfghjklzxcvbn";
		im.checkAlphabet(test);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckAlphabetWithIllegalCharacterLowerThenASCII65() {
		test = "qwertyuiopa@dfghjklzxcvbnm";
		im.checkAlphabet(test);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckAlphabetWithIllegalCharacterHigherThenASCII122() {
		test = "qwertyuiopa{dfghjklzxcvbnm";
		im.checkAlphabet(test);
	}

	@Test
	public void testCheckAlphabetWithUppercaseCharacter() {
		test = "qwertyuiopAsdfghjklzxcvbnm";
		assertEquals("qwertyuiopasdfghjklzxcvbnm", im.checkAlphabet(test));
	}
	
	@Test
	public void testKeyChecking(){
		test = "00001";
		assertEquals(test, im.checkKey(test,test.length()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWrongKeyChecking(){
		test = "00a01";
		assertEquals(test, im.checkKey(test,test.length()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testKeyLength(){
		test = "00001";
		assertEquals(test, im.checkKey(test,test.length()-1));
	}

}
