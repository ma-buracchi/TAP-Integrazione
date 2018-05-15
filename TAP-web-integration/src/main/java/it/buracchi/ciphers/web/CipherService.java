package it.buracchi.ciphers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import it.buracchi.ciphers.InputManager;
import it.buracchi.ciphers.Shift;
import it.buracchi.ciphers.Vigenere;

@Service
public class CipherService {
	
	@Autowired
	private Vigenere vig;
	
	@Autowired
	private Shift shift;
	
	@Autowired
	private Cifrario cifrario;

	public String chooseCipher(Model model) {
		cifrario = new Cifrario();
		model.addAttribute("cifrario", cifrario);
		return "index";
	}

	public String cipherSubmit(@ModelAttribute Cifrario cifrario) {
		return cifrario.getCipher();
	}

	public String vigenereComputing(@ModelAttribute Cifrario cifrario) {
		vig = new Vigenere(new InputManager(), cifrario.getKey());
		if (cifrario.getAction().equals("cifrare")) {
			cifrario.setCiphertext(vig.coding(cifrario.getPlaintext()));
		} else {
			cifrario.setCiphertext(vig.decoding(cifrario.getPlaintext()));
		}
		return "result";
	}

	public String shiftComputing(@ModelAttribute Cifrario cifrario) {
		shift = new Shift(new InputManager());
		if (cifrario.getAction().equals("cifrare")) {
			cifrario.setCiphertext(shift.coding(cifrario.getPlaintext(), Integer.parseInt(cifrario.getKey())));
		} else {
			cifrario.setCiphertext(shift.decoding(cifrario.getPlaintext(), Integer.parseInt(cifrario.getKey())));
		}
		return "result";
	}

}
