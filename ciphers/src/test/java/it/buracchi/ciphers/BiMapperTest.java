package it.buracchi.ciphers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BiMapperTest {

	private BiMapper<String, String> bimap;
	String key1 = "key1";
	String value1 = "value1";
	String key2 = "key2";
	String value2 = "value2";

	@Before
	public void setUp() throws Exception {
		bimap = new BiMapper<String, String>();
	}
	
	@Test
	public void testContainsZeroKey() {
		assertFalse(bimap.containsKey(key1));
	}

	@Test
	public void testAddingOneEntryAndContainsKey() {
		setOneElement();
		assertTrue(bimap.containsKey(key1));
	}

	@Test
	public void testAddingMoreThenOneEntryAndContainsKey() {
		setTwoElements();
		assertTrue(bimap.containsKey(key1));
		assertTrue(bimap.containsKey(key2));
	}

	@Test
	public void testSizeEmptyMap() {
		assertEquals(0, bimap.size());
	}

	@Test
	public void testSizeMapWithOneElement() {
		setOneElement();
		assertEquals(1, bimap.size());
	}

	@Test
	public void testSizeMapWithTwoElement() {
		setTwoElements();
		assertEquals(2, bimap.size());
	}

	@Test
	public void testGetValueFromKeyWhenThereIsNotKey() {
		assertEquals(null, bimap.getValueFromKey(key1));
	}

	@Test
	public void testGetValueFromKeyWhenThereIsJustOneKey() {
		setOneElement();
		assertEquals(value1, bimap.getValueFromKey(key1));
	}

	@Test
	public void testGetValueFromKeyWhenThereAreTwoKeys() {
		setTwoElements();
		assertEquals(value1, bimap.getValueFromKey(key1));
		assertEquals(value2, bimap.getValueFromKey(key2));
	}

	@Test
	public void testGetKeyFromValueWhenThereIsNotValue() {
		assertEquals(null, bimap.getKeyFromValue(value1));
	}

	@Test
	public void testGetKeyFromValueWhenThereIsJustOneKey() {
		setOneElement();
		assertEquals(key1, bimap.getKeyFromValue(value1));
	}

	@Test
	public void testGetKeyFromValueWhenThereAreTwoKeys() {
		setTwoElements();
		assertEquals(key1, bimap.getKeyFromValue(value1));
		assertEquals(key2, bimap.getKeyFromValue(value2));
	}

	@Test
	public void testContainsZeroValue() {
		assertFalse(bimap.containsValue(value1));
	}

	@Test
	public void testContainsOneValue() {
		setOneElement();
		assertTrue(bimap.containsValue(value1));
	}

	@Test
	public void testContainsTwoValue() {
		setTwoElements();
		assertTrue(bimap.containsValue(value1));
		assertTrue(bimap.containsValue(value2));
	}

	private void setOneElement() {
		bimap.addEntry(key1, value1);
	}

	private void setTwoElements() {
		bimap.addEntry(key1, value1);
		bimap.addEntry(key2, value2);
	}

}
