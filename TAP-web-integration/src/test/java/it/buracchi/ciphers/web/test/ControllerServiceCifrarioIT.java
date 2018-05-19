package it.buracchi.ciphers.web.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;

import it.buracchi.ciphers.web.CiphersWebController;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CiphersWebController.class)
public class ControllerServiceCifrarioIT {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testStatus200() throws Exception {
		mvc.perform(get("/")).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testReturnHomeView() throws Exception {
		ModelAndViewAssert.assertViewName(mvc.perform(get("/")).andReturn().getModelAndView(), "index");
	}

	@Test
	public void testAboutView() throws Exception {
		ModelAndViewAssert.assertViewName(mvc.perform(get("/about")).andReturn().getModelAndView(), "about");
	}

	@Test
	public void testErrorView() throws Exception {
		ModelAndViewAssert.assertViewName(mvc.perform(get("/error")).andReturn().getModelAndView(), "error");
	}

	@Test
	public void testAddedEmptyCipher() throws Exception {
		mvc.perform(get("/")).andExpect(view().name("index")).andExpect(model().attributeExists("cifrario"));
	}

	@Test
	public void postVigenere() throws Exception {
		mvc.perform(post("/cifratura").param("cipher", "vigenere").contentType(MediaType.APPLICATION_FORM_URLENCODED)

		).andExpect(status().isOk()).andExpect(view().name("vigenere"));
	}

	@Test
	public void postShift() throws Exception {
		mvc.perform(post("/cifratura").param("cipher", "shift").contentType(MediaType.APPLICATION_FORM_URLENCODED)

		).andExpect(status().isOk()).andExpect(view().name("shift"));
	}

	@Test
	public void shiftCipherCodingResultOK() throws Exception {
		mvc.perform(post("/resultShift").param("plaintext", "abc").param("key", "1").param("action", "cifrare")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		).andExpect(view().name("result"));
	}

	@Test
	public void shiftCipherDecodingResultOK() throws Exception {
		mvc.perform(post("/resultShift").param("plaintext", "bcd").param("key", "1").param("action", "decifrare")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		).andExpect(view().name("result"));
	}

	@Test
	public void vigenereCipherCodingResultOK() throws Exception {
		mvc.perform(post("/resultVigenere").param("plaintext", "abc").param("key", "b").param("action", "cifrare")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		).andExpect(view().name("result"));
	}

	@Test
	public void vigenereCipherDecodingResultOK() throws Exception {
		mvc.perform(post("/resultVigenere").param("plaintext", "bcd").param("key", "b").param("action", "decifrare")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)

		).andExpect(view().name("result"));
	}
}
