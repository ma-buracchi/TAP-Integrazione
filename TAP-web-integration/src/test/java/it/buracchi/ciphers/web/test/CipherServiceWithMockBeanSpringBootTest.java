package it.buracchi.ciphers.web.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import it.buracchi.ciphers.Shift;
import it.buracchi.ciphers.Vigenere;
import it.buracchi.ciphers.web.Cifrario;
import it.buracchi.ciphers.web.CipherService;

@RunWith(SpringRunner.class)
public class CipherServiceWithMockBeanSpringBootTest {
	
	@InjectMocks
	private CipherService cipherService;
	
	@MockBean
	private Cifrario cifrario;
	
	@MockBean
	private Vigenere vig;
	
	@MockBean
	private Shift shift;
	
	@Test
	public void vigenereOperationCifrareWithAllParameters() throws Exception {
		when(cifrario.getAction()).thenReturn("cifrare");
		when(cifrario.getKey()).thenReturn("b");
		when(cifrario.getPlaintext()).thenReturn("ciao");
		when(vig.coding("ciao")).thenReturn("djbp");
		assertThat(cipherService.vigenereComputing("cifrare", "ciao", vig)).isEqualTo("djbp");
	}
	
	@Test
	public void vigenereOperationDecifrareWithAllParameters() throws Exception {
		when(cifrario.getAction()).thenReturn("decifrare");
		when(cifrario.getKey()).thenReturn("b");
		when(cifrario.getPlaintext()).thenReturn("djbp");
		when(vig.decoding("djbp")).thenReturn("ciao");
		assertThat(cipherService.vigenereComputing("decifrare", "djbp", vig)).isEqualTo("ciao");
	}
	
	@Test
	public void shiftOperationCifrareWithAllParameters() throws Exception {
		when(cifrario.getAction()).thenReturn("cifrare");
		when(cifrario.getKey()).thenReturn("1");
		when(cifrario.getPlaintext()).thenReturn("ciao");
		when(shift.coding("ciao",1)).thenReturn("djbp");
		assertThat(cipherService.shiftComputing("cifrare", "ciao", "1", shift)).isEqualTo("djbp");
	}

	@Test
	public void shiftOperationDecifrareWithAllParameters() throws Exception {
		when(cifrario.getAction()).thenReturn("decifrare");
		when(cifrario.getKey()).thenReturn("1");
		when(cifrario.getPlaintext()).thenReturn("djbp");
		when(shift.decoding("djbp",1)).thenReturn("ciao");
		assertThat(cipherService.shiftComputing("decifrare", "djbp", "1", shift)).isEqualTo("ciao");
	}

}
