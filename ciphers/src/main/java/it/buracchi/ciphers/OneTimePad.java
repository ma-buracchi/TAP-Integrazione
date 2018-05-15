package it.buracchi.ciphers;

import java.security.SecureRandom;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static it.buracchi.ciphers.Constants.*;

public class OneTimePad {

	private Parser parser;
	private SecureRandom generator;
	private Mapper<Character, String> convert;
	private static final Logger LOGGER = LogManager.getLogger(OneTimePad.class);
	private static final char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public OneTimePad(Parser p, Mapper<Character, String> m, String[] conversion) {
		this.parser = p;
		this.convert = m;
		this.generator = new SecureRandom();
		initializeConvert(alphabet, conversion);
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure();
	}

	public String createKey(String msg) {
		LOGGER.info("Richiesta di creazione di una chiave per permettere la cifratura del messaggio " + msg);
		StringBuilder key = new StringBuilder();
		while (key.length() < msg.length()) {
			key.append(generator.nextInt(BIT_RANGE));
		}
		LOGGER.info("La chiave richiesta è -----> " + key.toString());
		return key.toString();
	}

	public String fromStringToBinary(String msg) {
		LOGGER.info("Richiesta di conversione della stringa " + msg + " in una stringa binaria");
		String res = stringToBin(parser.process(msg));
		LOGGER.info("La stringa binaria risultante è -----> " + res);
		return res;
	}

	public String fromBinaryToString(String message) {
		LOGGER.info("Richiesta di conversione della stringa binaria " + message + " in una stringa alfabetica");
		String res = binToString(message);
		LOGGER.info("La stringa alfabetica risultante è -----> " + res);
		return res;
	}

	public String coding(String message, String k) {
		LOGGER.info("Richiesta di cifratura del messaggio " + message + " utilizzando la chiave " + k);
		parser.checkKey(k, message.length());
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < message.length(); i++) {
			res.append(Character.getNumericValue(message.charAt(i)) ^ Character.getNumericValue(k.charAt(i)));
		}
		LOGGER.info("Il risultato della cifratura è -----> " + res.toString());
		return res.toString();
	}

	private String stringToBin(String msg) {
		StringBuilder res = new StringBuilder();
		for (Character c : msg.toCharArray()) {
			res.append(convert.getValueFromKey(c));
		}
		return res.toString();
	}

	private String binToString(String msg) {
		StringBuilder res = new StringBuilder();
		StringBuilder substring = new StringBuilder();
		for (char c : msg.toCharArray()) {
			substring.append(c);
			if (convert.containsValue(substring.toString())) {
				res.append(convert.getKeyFromValue(substring.toString()));
				substring.setLength(0);
			}
		}
		return res.toString();
	}

	private void initializeConvert(char[] alphabet, String[] conversion) {
		for (int i = 0; i < ALPHABET_LENGTH; i++) {
			convert.addEntry(alphabet[i], conversion[i]);
		}
	}

}
