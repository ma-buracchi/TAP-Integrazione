package it.buracchi.ciphers;

import static it.buracchi.ciphers.Constants.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Vigenere {

	private Parser parser;
	private String key;
	private static final Logger LOGGER = LogManager.getLogger(Vigenere.class);

	public Vigenere(Parser p, String key) {
		this.parser = p;
		this.key = parser.process(key);
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure();
	}

	public String getKey() {
		return key;
	}

	public String coding(String msg) {
		LOGGER.info("Richiesta di cifratura della stringa '" + msg + "' ");
		String message = parser.process(msg);
		if (key.length() < message.length()) {
			key = extendKey(key, message.length());
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < message.length(); i++) {
			int m = (int) (message.charAt(i)) - ASCII_OFFSET;
			int k = (int) (key.charAt(i)) - ASCII_OFFSET;
			result.append((char) (((m + k) % ALPHABET_LENGTH) + ASCII_OFFSET));
		}
		LOGGER.info("Il risultato della cifratura è -----> " + result.toString());
		return result.toString();
	}

	public String decoding(String msg) {
		LOGGER.info("Richiesta di decifratura della stringa '" + msg + "' ");
		String message = parser.process(msg);
		if (key.length() < message.length()) {
			key = extendKey(key, message.length());
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < message.length(); i++) {
			int m = (int) (message.charAt(i)) - ASCII_OFFSET;
			int k = (int) (key.charAt(i)) - ASCII_OFFSET;
			result.append((char) (((m - k + ALPHABET_LENGTH) % ALPHABET_LENGTH) + ASCII_OFFSET));
		}
		LOGGER.info("Il risultato della decifratura è -----> " + result.toString());
		return result.toString();
	}

	private static String extendKey(String key, int l) {
		LOGGER.info("Estensione della chiave '" + key + "' per raggiungere la lunghezza desiderata");
		if (key.isEmpty()) {
			throw new IllegalArgumentException("Key must be longer than 0");
		}
		StringBuilder k = new StringBuilder();
		while (k.length() < l) {
			k.append(key);
		}
		LOGGER.info("La chiave estesa è -----> " + k.toString());
		return k.toString();
	}

}
