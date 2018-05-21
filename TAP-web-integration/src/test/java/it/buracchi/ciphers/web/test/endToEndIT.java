package it.buracchi.ciphers.web.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlNumberInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlResetInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class endToEndIT {

	private WebClient webClient;
	private static final String HOMEPAGE = "http://localhost:8080";
	private static final String ABOUT = HOMEPAGE + "/about";

	@Before
	public void setup() {
		/*
		 * browsers CHROME NETSCAPE FIREFOX_38 FIREFOX_45 INTERNET_EXPLORER EDGE
		 */
		webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
	}

	@After
	public void close() {
		webClient.close();
	}

	@Test
	public void testHomePageTitle() throws Exception {
		HtmlPage homepage = webClient.getPage(HOMEPAGE);
		Assert.assertEquals("Home page", homepage.getTitleText());
	}

	@Test
	public void testAboutTitle() throws Exception {
		HtmlPage about = webClient.getPage(ABOUT);
		Assert.assertEquals("About", about.getTitleText());
	}

	@Test
	public void testSubmitVigenereCipherHome() throws Exception {
		HtmlPage homepage = webClient.getPage(HOMEPAGE);
		HtmlForm form = homepage.getFormByName("menu");
		HtmlRadioButtonInput radio = form.getInputByValue("vigenere");
		radio.click();
		HtmlSubmitInput submit = form.getInputByValue("Submit");
		HtmlPage result = submit.click();
		Assert.assertEquals("Vigenere cipher", result.getTitleText());
	}

	@Test
	public void testSubmitShiftCipherHome() throws Exception {
		HtmlPage homepage = webClient.getPage(HOMEPAGE);
		HtmlForm form = homepage.getFormByName("menu");
		HtmlRadioButtonInput radio = form.getInputByValue("shift");
		radio.click();
		HtmlSubmitInput submit = form.getInputByValue("Submit");
		HtmlPage result = submit.click();
		Assert.assertEquals("Shift cipher", result.getTitleText());
	}

	@Test
	public void testResetButtonHome() throws Exception {
		HtmlPage homepage = webClient.getPage(HOMEPAGE);
		HtmlForm form = homepage.getFormByName("menu");
		HtmlRadioButtonInput radio = form.getInputByValue("shift");
		radio.click();
		HtmlResetInput reset = form.getInputByValue("Reset");
		reset.click();
		HtmlSubmitInput submit = form.getInputByValue("Submit");
		HtmlPage result = submit.click();
		Assert.assertEquals("Vigenere cipher", result.getTitleText());
	}

	@Test
	public void testingVigenereCipher() throws Exception {
		HtmlPage homepage = webClient.getPage(HOMEPAGE);
		HtmlForm formHomepage = homepage.getFormByName("menu");
		HtmlSubmitInput submitHome = formHomepage.getInputByValue("Submit");
		HtmlPage vigenere = submitHome.click();
		HtmlForm formVig = vigenere.getFormByName("vigenere");
		HtmlTextInput plaintext = formVig.getInputByName("plaintext");
		plaintext.type("ciao");
		HtmlPasswordInput key = formVig.getInputByName("key");
		key.type("b");
		HtmlSubmitInput submitVig = formVig.getInputByValue("Submit");
		HtmlPage result = submitVig.click();
		Assert.assertEquals("Risultato", result.getTitleText());
		Assert.assertTrue(result.getBody().getTextContent().contains("ciao"));
		Assert.assertTrue(result.getBody().getTextContent().contains("djbp"));
	}
	
	@Test
	public void testingShiftCipher() throws Exception {
		HtmlPage homepage = webClient.getPage(HOMEPAGE);
		HtmlForm formHomepage = homepage.getFormByName("menu");
		HtmlRadioButtonInput radio = formHomepage.getInputByValue("shift");
		radio.click();
		HtmlSubmitInput submitHome = formHomepage.getInputByValue("Submit");
		HtmlPage shift = submitHome.click();
		HtmlForm formShift = shift.getFormByName("shift");
		HtmlTextInput plaintext = formShift.getInputByName("plaintext");
		plaintext.type("ciao");
		HtmlNumberInput key = formShift.getInputByName("key");
		key.type("1");
		HtmlSubmitInput submitShift = formShift.getInputByValue("Submit");
		HtmlPage result = submitShift.click();
		Assert.assertEquals("Risultato", result.getTitleText());
		Assert.assertTrue(result.getBody().getTextContent().contains("ciao"));
		Assert.assertTrue(result.getBody().getTextContent().contains("djbp"));
	}

}
