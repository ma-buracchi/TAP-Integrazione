package it.buracchi.ciphers.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.buracchi.ciphers.InputManager;
import it.buracchi.ciphers.Parser;
import it.buracchi.ciphers.Shift;
import it.buracchi.ciphers.Vigenere;

@Configuration
public class CipherServiceConfig {
	
	@Bean
	public CipherService cipherservice() {
		return new CipherService();
	}
	
	@Bean
	public Cifrario cifrario() {
		return new Cifrario();
	}
	
	@Bean
	public Parser parser() {
		return new InputManager();
	}
	
	@Bean
	public Vigenere vig() {
		return new Vigenere(new InputManager(), "");
	}
	
	@Bean
	public Shift shift() {
		return new Shift(new InputManager());
	}

}
