package it.buracchi.ciphers.web.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import it.buracchi.ciphers.InputManager;
import it.buracchi.ciphers.Shift;
import it.buracchi.ciphers.web.CipherService;

public class ServiceShiftIT {
	
	private CipherService cipherservice;
	private Shift shift;
	
	@Before
	public void Setup() {
		cipherservice = new CipherService();
		shift = new Shift(new InputManager());
	}
	
	@Test
	public void vigenereComputingCifrare() throws Exception {
		assertThat(cipherservice.shiftComputing("cifrare", "ciao", "1", shift)).isEqualTo("djbp");
	}
	
	@Test
	public void vigenereComputingDecifrare() throws Exception {
		assertThat(cipherservice.shiftComputing("decifrare", "djbp", "1", shift)).isEqualTo("ciao");
	}

}
