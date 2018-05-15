package it.buracchi.ciphers.web;

import org.springframework.stereotype.Service;

@Service
public class Cifrario {
	private String plaintext;
	private String cipher = "vigenere";
	private String key;
	private String ciphertext;
	private String action = "cifrare";
	
	public String getPlaintext() {
		return plaintext;
	}

	public void setPlaintext(String plain) {
		this.plaintext = plain;
	}
	
	public String getCipher() {
		return cipher;
	}

	public void setCipher(String cif) {
		this.cipher = cif;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCiphertext() {
		return ciphertext;
	}

	public void setCiphertext(String ciphertext) {
		this.ciphertext = ciphertext;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
}
