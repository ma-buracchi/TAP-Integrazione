package it.buracchi.ciphers;

public interface Mapper<K, V> {
	
	void addEntry(K k, V v);
	
	int size();

	V getValueFromKey(K k);
	
	K getKeyFromValue(V v);

	boolean containsKey(K k);

	boolean containsValue(V v);

}
