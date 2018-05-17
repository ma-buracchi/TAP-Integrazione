package it.buracchi.ciphers.web;

import org.springframework.stereotype.Service;
import it.buracchi.ciphers.Shift;
import it.buracchi.ciphers.Vigenere;

@Service
public class CipherService {

	public String vigenereComputing(String action, String plaintext, Vigenere vig) {
		if (action.equals("cifrare")) {
			return vig.coding(plaintext);
		} else {
			return vig.decoding(plaintext);
		}
	}

	public String shiftComputing(String action, String plaintext, String key, Shift shift) {
		if (action.equals("cifrare")) {
			return shift.coding(plaintext, Integer.parseInt(key));
		} else {
			return shift.decoding(plaintext, Integer.parseInt(key));
		}
	}

}
