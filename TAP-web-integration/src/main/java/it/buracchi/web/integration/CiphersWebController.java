package it.buracchi.web.integration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import it.buracchi.web.models.Cifrario;
import it.buracchi.web.services.CipherService;

@Controller
public class CiphersWebController {

	private CipherService cipherService = new CipherService();
	
	@GetMapping("/")
	public String choosingCipher(Model model) {		
		return cipherService.chooseCipher(model);
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
		return cipherService.cipherSubmit(cifrario);
	}

	@PostMapping("/resultVigenere")
	public String vigenereComputation(@ModelAttribute Cifrario cifrario) {
		return cipherService.vigenereComputing(cifrario);
	}

	@PostMapping("/resultShift")
	public String shiftCompute(@ModelAttribute Cifrario cifrario) {
		return cipherService.shiftComputing(cifrario);
	}

}