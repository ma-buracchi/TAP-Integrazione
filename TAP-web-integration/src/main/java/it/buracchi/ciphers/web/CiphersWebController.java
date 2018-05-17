package it.buracchi.ciphers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.buracchi.ciphers.InputManager;
import it.buracchi.ciphers.Shift;
import it.buracchi.ciphers.Vigenere;

@Controller
public class CiphersWebController {

	@Autowired
	private CipherService cipherService;

	@Autowired
	private Cifrario cifrario;

	@GetMapping("/")
	public String choosingCipher(Model model) {
		model.addAttribute("cifrario", cifrario);
		return "index";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("/error")
	public String error() {
		return "error";
	}

	@PostMapping("/cifratura")
	public String cipherSubmission(@ModelAttribute Cifrario cifrario) {
		return cifrario.getCipher();
	}

	@PostMapping("/resultVigenere")
	public String vigenereComputation(@ModelAttribute Cifrario cifrario) {
		cifrario.setCiphertext(cipherService.vigenereComputing(cifrario.getAction(), cifrario.getPlaintext(),
				new Vigenere(new InputManager(), cifrario.getKey())));
		return "result";
	}

	@PostMapping("/resultShift")
	public String shiftCompute(@ModelAttribute Cifrario cifrario) {
		cifrario.setCiphertext(cipherService.shiftComputing(cifrario.getAction(), cifrario.getPlaintext(), cifrario.getKey(),
				new Shift(new InputManager())));
		return "result";
	}

}