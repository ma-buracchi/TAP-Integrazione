package it.buracchi.ciphers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class SubstitutionTest {

	private Substitution sub;
	private Parser parser;
	private Mapper<Character, Character> convertTable;
	private String newAlphabet;

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		parser = mock(Parser.class);
		convertTable = mock(Mapper.class);
		newAlphabet = "qwertyuiopasdfghjklzxcvbnm";
	}

	@Test
	public void testCoding() {
		newAlphabetSetup();
		when(parser.process("a")).thenReturn("a");
		when(convertTable.getValueFromKey('a')).thenReturn('q');
		assertEquals("q", sub.coding("a"));
	}

	@Test
	public void testCodingLongerString() {
		newAlphabetSetup();
		when(parser.process("test")).thenReturn("test");
		when(convertTable.getValueFromKey('t')).thenReturn('z');
		when(convertTable.getValueFromKey('e')).thenReturn('t');
		when(convertTable.getValueFromKey('s')).thenReturn('l');
		assertEquals("ztlz", sub.coding("test"));
	}

	@Test
	public void testCodingEmptyString() {
		newAlphabetSetup();
		when(parser.process("")).thenReturn("");
		assertEquals("", sub.coding(""));
	}

	@Test
	public void testDecoding() {
		newAlphabetSetup();
		when(parser.process("q")).thenReturn("q");
		when(convertTable.getKeyFromValue('q')).thenReturn('a');
		assertEquals("a", sub.decoding("q"));
	}

	@Test
	public void testDecodingLongerString() {
		newAlphabetSetup();
		when(parser.process("ztlz")).thenReturn("ztlz");
		when(convertTable.getKeyFromValue('z')).thenReturn('t');
		when(convertTable.getKeyFromValue('t')).thenReturn('e');
		when(convertTable.getKeyFromValue('l')).thenReturn('s');
		assertEquals("test", sub.decoding("ztlz"));
	}

	@Test
	public void testDecodingEmptyString() {
		newAlphabetSetup();
		when(parser.process("")).thenReturn("");
		assertEquals("", sub.decoding(""));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewAlphabetShorterThenTwentySixLetters() {
		newAlphabet = "qwertyuiopasdfghj";
		when(parser.checkAlphabet(newAlphabet)).thenThrow(new IllegalArgumentException());
		sub = new Substitution(parser, convertTable, newAlphabet);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewAlphabetLongerThenTwentySixLetters() {
		newAlphabet = "qwertyuiopasdfghjklzxcvbnmqwerty";
		when(parser.checkAlphabet(newAlphabet)).thenThrow(new IllegalArgumentException());
		sub = new Substitution(parser, convertTable, newAlphabet);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewAlphabetWithIllegalCharacter() {
		newAlphabet = "qwertyuiopa?dfghjklzxcvbnm";
		when(parser.checkAlphabet(newAlphabet)).thenThrow(new IllegalArgumentException());
		sub = new Substitution(parser, convertTable, newAlphabet);
	}
	
	private void newAlphabetSetup() {
		when(parser.checkAlphabet(newAlphabet)).thenReturn(newAlphabet);
		sub = new Substitution(parser, convertTable, newAlphabet);
	}

}
