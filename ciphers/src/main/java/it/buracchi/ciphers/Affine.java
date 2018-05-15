package it.buracchi.ciphers;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import static it.buracchi.ciphers.Constants.*;

public class Affine {

	private Parser parser;
	private Mapper<Integer, Integer> inverse;
	private static final int[] RELATIVELY_PRIME_TO_26 = { 1, 3, 5, 7, 11, 17, 25 };
	private static final int[] INVERSE_MOD_26 = { 1, 9, 21, 15, 19, 23, 25 };
	private static final Logger LOGGER = LogManager.getLogger(Affine.class);

	public Affine(Parser p, Mapper<Integer, Integer> m) {
		parser = p;
		inverse = m;
		setInverse();
		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure();
	}

	private void setInverse() {
		for (int i = 0; i < RELATIVELY_PRIME_TO_26.length; i++) {
			inverse.addEntry(RELATIVELY_PRIME_TO_26[i], INVERSE_MOD_26[i]);
		}
	}

	public String coding(String msg, int a, int b) {
		LOGGER.info("richiesta di cifratura della stringa '" + msg + "' con parametri a = " + a + " e b = " + b);
		String message = parser.process(msg);
		StringBuilder res = new StringBuilder();
		if (inverse.containsKey(a)) {
			for (Character c : message.toCharArray()) {
				int letter = c - ASCII_OFFSET;
				res.append((char) (((a * letter + b) % ALPHABET_LENGTH) + ASCII_OFFSET));
			}
			LOGGER.info("risultato della cifratura -----> " + res.toString());
			return res.toString();
		} else {
			throw new IllegalArgumentException("Coefficient 'a' must be choosen between 1,3,5,7,11,17,25");
		}
	}

	public String decoding(String msg, int a, int b) {
		LOGGER.info("richiesta di decifratura della stringa '" + msg + "' con parametri a = " + a + " e b = " + b);
		String message = parser.process(msg);
		StringBuilder res = new StringBuilder();
		for (Character c : message.toCharArray()) {
			int letter = c - ASCII_OFFSET;
			res.append((char) (((inverse.getValueFromKey(a) * (letter - b + NEGATIVE_AVOIDING)) % ALPHABET_LENGTH)
					+ ASCII_OFFSET));
		}
		LOGGER.info("risultato della decifratura -----> " + res.toString());
		return res.toString();
	}

}
