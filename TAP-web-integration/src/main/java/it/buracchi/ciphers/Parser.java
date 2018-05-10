package it.buracchi.ciphers;

public interface Parser {
	
	String process(String msg);
	String checkAlphabet(String alphabet);
	String checkKey(String key, int l);

}
