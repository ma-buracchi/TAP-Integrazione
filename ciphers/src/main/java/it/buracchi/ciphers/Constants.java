package it.buracchi.ciphers;

public final class Constants {

	public static final int NEGATIVE_AVOIDING = (int) Math.pow(26, 2);
	public static final int ASCII_OFFSET = 97;
	public static final int ALPHABET_LENGTH = 26;
	public static final int ASCII_A_LOWERCASE = 65;
	public static final int ASCII_Z = 122;
	public static final int ASCII_0 = 48;
	public static final int ASCII_1 = 49;
	public static final int CHARACTER_LENGTH_IN_BIT = 5;
	public static final int BIT_RANGE = 2;
	
	private Constants() {
		throw new UnsupportedOperationException("Utility class");
	}

}
