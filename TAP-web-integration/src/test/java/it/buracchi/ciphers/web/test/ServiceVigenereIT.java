package it.buracchi.ciphers.web.test;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import it.buracchi.ciphers.InputManager;
import it.buracchi.ciphers.Vigenere;
import it.buracchi.ciphers.web.CipherService;

public class ServiceVigenereIT {
	
	private CipherService cipherservice;
	private Vigenere vig;
	
	@Before
	public void Setup() {
		cipherservice = new CipherService();
		vig = new Vigenere(new InputManager(), "b");
	}
	
	@Test
	public void vigenereComputingCifrare() throws Exception {
		assertThat(cipherservice.vigenereComputing("cifrare", "ciao", vig)).isEqualTo("djbp");
	}
	
	@Test
	public void vigenereComputingDecifrare() throws Exception {
		assertThat(cipherservice.vigenereComputing("decifrare", "djbp", vig)).isEqualTo("ciao");
	}
	
}
