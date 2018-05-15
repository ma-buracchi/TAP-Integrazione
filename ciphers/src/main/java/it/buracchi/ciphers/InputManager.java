package it.buracchi.ciphers;

import java.util.Locale;
import java.util.regex.Pattern;

import static it.buracchi.ciphers.Constants.*;

public class InputManager implements Parser {

	@Override
	public String process(String msg) {
		return msg.replaceAll("[^A-Za-z]", "").toLowerCase(Locale.ENGLISH);
	}

	@Override
	public String checkAlphabet(String alphabet) {
		if (alphabet.length() != ALPHABET_LENGTH) {
			throw new IllegalArgumentException("New alphabet must be 26 characters long");
		}
		for (Character c : alphabet.toCharArray()) {
			if (Pattern.matches("[^A-Za-z]", c.toString())) {
				throw new IllegalArgumentException("Letters from A to z are the only available characters");
			}
		}
		return alphabet.toLowerCase(Locale.ENGLISH);
	}

	@Override
	public String checkKey(String key, int l) {
		if (key.length() != l) {
			throw new IllegalArgumentException("Key length and message length must be the same");
		}
		for (Character c : key.toCharArray()) {
			if (Pattern.matches("[^01]", c.toString())) {
				throw new IllegalArgumentException("Key must be composed just by 0 or 1");
			}
		}
		return key;
	}

}
