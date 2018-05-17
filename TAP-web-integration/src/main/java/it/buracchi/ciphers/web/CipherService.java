package it.buracchi.ciphers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import it.buracchi.ciphers.Shift;
import it.buracchi.ciphers.Vigenere;

@Service
public class CipherService {

	@Autowired
	public CipherService() {
	}

	public String vigenereComputing(@ModelAttribute Cifrario cifrario, String action, String plaintext, Vigenere vig) {
		if (action.equals("cifrare")) {
			return vig.coding(plaintext);
		} else {
			return vig.decoding(plaintext);
		}
	}

	public String shiftComputing(@ModelAttribute Cifrario cifrario, String action, String plaintext, Shift shift) {
		if (action.equals("cifrare")) {
			return shift.coding(cifrario.getPlaintext(), Integer.parseInt(cifrario.getKey()));
		} else {
			return shift.decoding(cifrario.getPlaintext(), Integer.parseInt(cifrario.getKey()));
		}
	}

}
