package it.buracchi.web.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import it.buracchi.web.models.Cifrario;
import it.buracchi.web.services.CipherService;
import it.buracchi.web.config.CipherServiceConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=CipherServiceConfig.class)
public class CipherServiceWithMockBeanSpringBootTest {
	
	@Autowired
	private CipherService cipherService;
	
	@MockBean
	private Cifrario cifrario;
	
	@Test
	public void choosenVigenereCipher() throws Exception {
		when(cifrario.getCipher()).thenReturn("vigenere");
		assertThat(cipherService.cipherSubmit(cifrario)).isEqualTo("vigenere");
	}
	
	@Test
	public void choosenShiftCipher() throws Exception {
		when(cifrario.getCipher()).thenReturn("shift");
		assertThat(cipherService.cipherSubmit(cifrario)).isEqualTo("shift");
	}
	
	@Test
	public void vigenereOperationCifrareWithAllParameters() throws Exception {
		when(cifrario.getAction()).thenReturn("cifrare");
		when(cifrario.getKey()).thenReturn("b");
		when(cifrario.getPlaintext()).thenReturn("ciao");
		assertThat(cipherService.vigenereComputing(cifrario)).isEqualTo("result");
	}
	
	@Test
	public void vigenereOperationCifrareWithoutKey() throws Exception {
		when(cifrario.getAction()).thenReturn("cifrare");
		when(cifrario.getKey()).thenReturn("");
		when(cifrario.getPlaintext()).thenReturn("ciao");
		assertThat(cipherService.vigenereComputing(cifrario)).isEqualTo("result");
	}
	
	@Test
	public void vigenereOperationCifrareWithoutMessage() throws Exception {
		when(cifrario.getAction()).thenReturn("cifrare");
		when(cifrario.getKey()).thenReturn("b");
		when(cifrario.getPlaintext()).thenReturn("");
		assertThat(cipherService.vigenereComputing(cifrario)).isEqualTo("result");
	}
	
	@Test
	public void vigenereOperationCifrareWithoutMessageAndKey() throws Exception {
		when(cifrario.getAction()).thenReturn("cifrare");
		when(cifrario.getKey()).thenReturn("");
		when(cifrario.getPlaintext()).thenReturn("");
		assertThat(cipherService.vigenereComputing(cifrario)).isEqualTo("result");
	}
	
	@Test
	public void vigenereOperationDecifrareWithAllParameters() throws Exception {
		when(cifrario.getAction()).thenReturn("decifrare");
		when(cifrario.getKey()).thenReturn("b");
		when(cifrario.getPlaintext()).thenReturn("djbp");
		assertThat(cipherService.vigenereComputing(cifrario)).isEqualTo("result");
	}
	
	@Test
	public void vigenereOperationDecifrareWithoutKey() throws Exception {
		when(cifrario.getAction()).thenReturn("decifrare");
		when(cifrario.getKey()).thenReturn("");
		when(cifrario.getPlaintext()).thenReturn("djbp");
		assertThat(cipherService.vigenereComputing(cifrario)).isEqualTo("result");
	}
	
	@Test
	public void vigenereOperationDecifrareWithoutMessage() throws Exception {
		when(cifrario.getAction()).thenReturn("decifrare");
		when(cifrario.getKey()).thenReturn("b");
		when(cifrario.getPlaintext()).thenReturn("");
		assertThat(cipherService.vigenereComputing(cifrario)).isEqualTo("result");
	}
	
	@Test
	public void vigenereOperationDecifrareWithoutMessageAndKey() throws Exception {
		when(cifrario.getAction()).thenReturn("decifrare");
		when(cifrario.getKey()).thenReturn("");
		when(cifrario.getPlaintext()).thenReturn("");
		assertThat(cipherService.vigenereComputing(cifrario)).isEqualTo("result");
	}
	
	@Test
	public void shiftOperationCifrareWithAllParameters() throws Exception {
		when(cifrario.getAction()).thenReturn("cifrare");
		when(cifrario.getKey()).thenReturn("1");
		when(cifrario.getPlaintext()).thenReturn("ciao");
		assertThat(cipherService.shiftComputing(cifrario)).isEqualTo("result");
	}
	
	@Test
	public void shiftOperationCifrareWithoutMessage() throws Exception {
		when(cifrario.getAction()).thenReturn("cifrare");
		when(cifrario.getKey()).thenReturn("1");
		when(cifrario.getPlaintext()).thenReturn("");
		assertThat(cipherService.shiftComputing(cifrario)).isEqualTo("result");
	}

	@Test
	public void shiftOperationDecifrareWithAllParameters() throws Exception {
		when(cifrario.getAction()).thenReturn("decifrare");
		when(cifrario.getKey()).thenReturn("1");
		when(cifrario.getPlaintext()).thenReturn("djbp");
		assertThat(cipherService.shiftComputing(cifrario)).isEqualTo("result");
	}
	
	@Test
	public void shiftOperationDecifrareWithoutMessage() throws Exception {
		when(cifrario.getAction()).thenReturn("decifrare");
		when(cifrario.getKey()).thenReturn("1");
		when(cifrario.getPlaintext()).thenReturn("");
		assertThat(cipherService.shiftComputing(cifrario)).isEqualTo("result");
	}

}
