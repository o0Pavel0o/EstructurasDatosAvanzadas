package test;

import static org.junit.Assert.*;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import material.maps.Entry;
import material.maps.HashTableMapSC;

public class HashTableMapSCTest {

	public void setUp(HashTableMapSC<Integer, String> testMap) throws Exception {
        testMap.put(1, "uno");
        testMap.put(2, "dos");
        testMap.put(3, "tres");
        testMap.put(4, "cuatro");
        testMap.put(5, "cinco");
        testMap.put(6, "seis");
        testMap.put(7, "siete");
        testMap.put(8, "ocho");
        testMap.put(9, "nueve");
        testMap.put(10, "diez");
        testMap.put(11, "once");
        testMap.put(12, "doce");
        testMap.put(13, "trece");
        testMap.put(14, "catorce");
        testMap.put(15, "quince");
    }

    @Test
    public void testSize() throws Exception {
    	HashTableMapSC<Integer, String> testMap = new HashTableMapSC<Integer, String>();
        assertEquals(testMap.size(), 0);
    }

    @Test
    public void testIsEmpty() throws Exception {
    	HashTableMapSC<Integer, String> testMap = new HashTableMapSC<Integer, String>();
        assertTrue(testMap.isEmpty());
    }

    @Test
    public void testKeySet() throws Exception {
    	HashTableMapSC<Integer, String> testMap = new HashTableMapSC<Integer, String>();
        Iterable<Integer> keySet = testMap.keys();
        Iterator<Integer> iterator = keySet.iterator();
        List<Integer> keyList = new ArrayList<Integer>();
        while (iterator.hasNext()) {
            keyList.add(iterator.next());
        }
        assertEquals(keyList.size(), 0);
    }

    @Test
    public void testPut() throws Exception {

    	HashTableMapSC<Integer, String> testMap = new HashTableMapSC<Integer, String>();
        assertNull(testMap.put(5, "cinco"));
        assertNull(testMap.put(6, "seis"));
        assertEquals(testMap.size(), 2);

    }

    @Test
    public void testRemove() throws Exception {
    	HashTableMapSC<Integer, String> testMap = new HashTableMapSC<Integer, String>();
        assertNull(testMap.remove(6));
    }

    @Test
    public void testEntrySet() throws Exception {
    	HashTableMapSC<Integer, String> testMap = new HashTableMapSC<Integer, String>();
    	testMap.put(1, "lunes");
    	testMap.put(2, "martes");
    	testMap.put(3, "miercoles");
    	testMap.put(4, "jueves");
    	testMap.put(5, "viernes");
    	testMap.put(6, "sabado");
    	testMap.put(7, "domingo");
    	for(Entry<Integer, String> e:testMap.entries()) {
    	  if(e.getValue().equals("lunes"))assertEquals(1, e.getValue());
      	  if(e.getValue().equals("martes"))assertEquals(2, e.getValue());
      	  if(e.getValue().equals("miercoles"))assertEquals(3, e.getValue());
      	  if(e.getValue().equals("jueves"))assertEquals(4, e.getValue());
    	}
    	
        
    }

    @Test
    public void testValues() throws Exception {
    	HashTableMapSC<Integer, String> testMap = new HashTableMapSC<Integer, String>();
        Iterable<String> values = testMap.values();
        Iterator<String> iterator = values.iterator();
        List<String> valuesList = new ArrayList<String>();
        while (iterator.hasNext()) {
            valuesList.add(iterator.next());
        }
        assertEquals(valuesList.size(), 0);
        assertTrue(valuesList.contains("uno"));
        assertTrue(valuesList.contains("dos"));
        assertTrue(valuesList.contains("tres"));
        assertTrue(valuesList.contains("cuatro"));
        assertTrue(valuesList.contains("cinco"));
        assertTrue(valuesList.contains("seis"));
        assertTrue(valuesList.contains("siete"));
        assertTrue(valuesList.contains("ocho"));
        assertTrue(valuesList.contains("nueve"));
        assertTrue(valuesList.contains("diez"));
        assertTrue(valuesList.contains("once"));
        assertTrue(valuesList.contains("doce"));
        assertTrue(valuesList.contains("trece"));
        assertTrue(valuesList.contains("catorce"));
        assertTrue(valuesList.contains("quince"));
    }

    @Test
    public void insertMany() {
    	HashTableMapSC<Integer, String> testMap = new HashTableMapSC<Integer, String>();
        int ammountOfEntriesInserted = 100;
        for (int i = 0; i < ammountOfEntriesInserted; i++) {
            testMap.put(i, "valor " + i);
        }
        assertEquals(testMap.size(), ammountOfEntriesInserted);
    }
    

    @Test
    public void testRehash() {
        HashTableMapSC<Integer, Integer> tabla = new HashTableMapSC<Integer, Integer>(10);
        final int num = 1000;
        for (int j=0; j<num; j++) {
        	tabla.put(j, j);
        }
        assertEquals(tabla.size(), num);
        tabla = new HashTableMapSC<Integer, Integer>(10);
        int i, j;
        for (j=1; j<=num; j++) {
        	tabla.put(j, j);
            i = 1;
            while ((tabla.get(i) != null) && (i<=j)) {
                i++;
            }
            assertEquals(i, j+1);
        }
    }
}