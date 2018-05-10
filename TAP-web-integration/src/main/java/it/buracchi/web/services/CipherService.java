package it.buracchi.web.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import it.buracchi.ciphers.InputManager;
import it.buracchi.ciphers.Shift;
import it.buracchi.ciphers.Vigenere;
import it.buracchi.web.models.Cifrario;

@Service
public class CipherService {

	public String chooseCipher(Model model) {
		model.addAttribute("cifrario", new Cifrario());
		return "index";
	}

	public String cipherSubmit(@ModelAttribute Cifrario cifrario) {
		return cifrario.getCipher();
	}

	public String vigenereComputing(@ModelAttribute Cifrario cifrario) {
		Vigenere vig;
		if (cifrario.getKey().isEmpty()) {
			vig = new Vigenere(new InputManager(), "a");
		} else {
			vig = new Vigenere(new InputManager(), cifrario.getKey());
		}
		if (cifrario.getAction().equals("cifrare")) {
			cifrario.setCiphertext(vig.coding(cifrario.getPlaintext()));
		} else {
			cifrario.setCiphertext(vig.decoding(cifrario.getPlaintext()));
		}
		return "result";
	}

	public String shiftComputing(@ModelAttribute Cifrario cifrario) {
		Shift shift = new Shift(new InputManager());
		if (cifrario.getAction().equals("cifrare")) {
			cifrario.setCiphertext(shift.coding(cifrario.getPlaintext(), Integer.parseInt(cifrario.getKey())));
		} else {
			cifrario.setCiphertext(shift.decoding(cifrario.getPlaintext(), Integer.parseInt(cifrario.getKey())));
		}
		return "result";
	}

}
