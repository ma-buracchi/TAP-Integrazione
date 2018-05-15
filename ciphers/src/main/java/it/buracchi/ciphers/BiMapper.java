package it.buracchi.ciphers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class BiMapper<K, V> implements Mapper<K, V> {

	private BiMap<K, V> map;
	
	public BiMapper(){
		map = HashBiMap.create();
	}
	
	@Override
	public void addEntry(K k, V v) {
		map.put(k, v);
	}
	
	@Override
	public int size() {
		return map.size();
	}

	@Override
	public V getValueFromKey(K k) {
		return map.get(k);
	}

	@Override
	public K getKeyFromValue(V v) {
		return map.inverse().get(v);
	}

	@Override
	public boolean containsKey(K k) {
		return map.containsKey(k);
	}

	@Override
	public boolean containsValue(V v) {
		return map.containsValue(v);
	}

}
