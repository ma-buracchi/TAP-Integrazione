package it.buracchi.ciphers;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public final class Main {

	public static final int A = 3;
	public static final int B = 5;
	private static final Logger LOGGER = LogManager.getLogger(Main.class);

	private Main() {
		throw new UnsupportedOperationException("Utility class");
	}

	public static void main(String[] args) {

		Parser parser = new InputManager();
		Mapper<Integer, Integer> affineMapper = new BiMapper<>();
		Affine aff = new Affine(parser, affineMapper);
		Mapper<Character, String> otpMapper = new BiMapper<>();
		String[] huffman = { "0100", "011111", "11110", "01010", "000", "10110", "011100", "1101", "0010",
				"101111100", "1011110", "01011", "10100", "0011", "0110", "011110", "101111110", "1110", "1100", "100",
				"11111", "101110", "10101", "101111101", "011101", "101111111" };
		OneTimePad otp = new OneTimePad(parser,otpMapper,huffman);
		Shift shift = new Shift(parser);
		Mapper<Character, Character> subMapper = new BiMapper<>();
		Substitution sub = new Substitution(parser, subMapper,"qwertyuiopasdfghjklzxcvbnm");
		Vigenere vig = new Vigenere(parser, "test");

		BasicConfigurator.resetConfiguration();
		BasicConfigurator.configure();

		LOGGER.info("Benvenuti in ciphers, progetto per esame di Tecniche Avanzate di Programmazione");
		LOGGER.info("In questo progetto assumiamo che 'P' (plaintext) rappresenti il messaggio in chiaro, "
				+ "'C' (ciphertext) rappresenti il messaggio cifrato, C[i] l'i-esimo carattere del messaggio "
				+ "cifrato e P[i] l'i-esimo carattere del messaggio in chiaro");
		LOGGER.info("Vediamo tutte le funzionalità implementate dai vari cifrari");
		LOGGER.info("**************Affine cipher**************");
		LOGGER.info("Per quanto riguarda l'Affine cipher, dati due parametri 'a' e 'b', "
				+ "i messaggi verranno cifrati con la formula C[i] = (a*P[i] + b)%26");
		LOGGER.info(
				"Proviamo a cifrare la stringa 'test' usando come parametri a=3 e b=5. Dovremmo ottenere la stringa 'krhk'");
		LOGGER.info("Proviamo:");
		aff.coding("test", A, B);
		LOGGER.info("Come controprova decifriamo, usando gli stessi parametri, la stringa appena ottenuta");
		aff.decoding(aff.coding("test", A, B), A, B);
		LOGGER.info("**************OneTimePad cipher**************");
		LOGGER.info("OneTimePad cipher ha bisogno di una codifica binaria 'B' del messaggio da cifrare e "
				+ "di una chiave 'K' lunga almeno quanto 'B'.");
		LOGGER.info("I messaggi verranno cifrati con la formula C[i] = (B[i] XOR K[i]");
		LOGGER.info("Anche in questo caso vogliamo cifrare la parola 'test'");
		LOGGER.info("Innanzi tutto facciamoci convertire la parola test in una stringa binaria "
				+ "utilizzando la codifica di Huffmann relativa all'alfabeto inglese");
		String test = otp.fromStringToBinary("test");
		LOGGER.info("A questo punto, facciamoci creare una chiave casuale lunga almeno quanto il messaggio da cifrare");
		String key = otp.createKey(test);
		LOGGER.info("A questo punto procediamo con la cifratura");
		String res = otp.coding(test, key);
		LOGGER.info("Come controprova decifriamo con la stessa chiave la stringa appena ottenuta");
		otp.fromBinaryToString(otp.coding(res, key));
		LOGGER.info("**************Shift cipher**************");
		LOGGER.info(
				"Shift cipher, dato un parametro 'a', semplicemente cifra il messaggio con la formula C[i] = (P[i] + a)%26");
		LOGGER.info("La versione con a = 3 è anche nota come CIFRARIO DI GIULIO CESARE");
		LOGGER.info("Proviamo a cifrare 'test' anche con questo cifrario con a = 1");
		LOGGER.info("Il risultato che ci aspettiamo è 'uftu'");
		shift.coding("test", 1);
		LOGGER.info("Effettuiamo la solita controprova");
		shift.decoding("uftu", 1);
		LOGGER.info("**************Substitution cipher**************");
		LOGGER.info("Substitution cipher ha bisogno di una permutazione dell'alfabeto inglese 'N'. "
				+ "Il messaggio cifrato sarà quindi C[i] = N[P[i]]");
		LOGGER.info("Proviamo a cifrare 'test' con N = qwertyuiopasdfghjklzxcvbnm");
		LOGGER.info("Mettendo in colonna i due alfabeti è semplice verificare che utilizzando questa permutazione "
				+ "ci aspettiamo di ricevere come cifratura la stringa 'ztlz'");
		LOGGER.info("abcdefghijklmnopqrstuvwxyz");
		LOGGER.info("qwertyuiopasdfghjklzxcvbnm");
		LOGGER.info("Procediamo");
		sub.coding("test");
		LOGGER.info("Controprova");
		sub.decoding("ztlz");
		LOGGER.info("**************Vigenere cipher**************");
		LOGGER.info("Vigenere cipher ha bisogno di una parola chiave 'K' che verrà ripetuta fino al "
				+ "raggiungimento della dimensione del messaggio da cifrare. Il messaggio cifrato sarà quindi "
				+ "C[i] = (P[i] + K[i]) % 26");
		LOGGER.info("Proviamo a cifrare 'testmessage' con K = test");
		vig.coding("testmessage");
		LOGGER.info("Controprova");
		vig.decoding("mikmfikltkw");

	}

}
