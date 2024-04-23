package test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import org.junit.jupiter.api.Test;

import main.QuadraticSpaceHashing;

public class QuadraticSpaceHashingTest {
    public static final int MAX_ARRAY_SIZE = 1000000;
    public static final int MAX_STRING_SIZE = 100;
    private QuadraticSpaceHashing<Integer> intTable = new QuadraticSpaceHashing<>(1);
    private QuadraticSpaceHashing<String> stringTable = new QuadraticSpaceHashing<>(1);
    private QuadraticSpaceHashing<Double> doubleTable = new QuadraticSpaceHashing<>(1);
    private QuadraticSpaceHashing<Character> charTable = new QuadraticSpaceHashing<>(1);

    
   
    @Test
    void testBatchDeleteIntegers() {
        ArrayList<Integer> intData = new ArrayList<>(Arrays.asList(1, 5, 111, 4, 6, 2, -2));
        assertArrayEquals(new int[] { 7, 0 }, intTable.batchInsert(intData,intData.size()+intTable.getSize()));
        ArrayList<Integer> deleteData = new ArrayList<>(Arrays.asList(3, 2, 11, 2, 0));
        int[] expectedResults = { 1, 4 };
        assertArrayEquals(expectedResults, intTable.batchDelete(deleteData));
    }

    @Test
    void testBatchDeleteDoubles() {
        ArrayList<Double> doubleData = new ArrayList<>(Arrays.asList(1.9, -5.6, 111.0, 41213.3, 6.0, 2.2));
        assertArrayEquals(new int[] { 6, 0 }, doubleTable.batchInsert(doubleData,doubleData.size()+doubleTable.getSize()));
        ArrayList<Double> deleteData = new ArrayList<>(Arrays.asList(3.0, 2.22, -11.5, -5.6, 0.0));
        int[] expectedResults = { 1, 4 };
        assertArrayEquals(expectedResults, doubleTable.batchDelete(deleteData));
    }

    @Test
    void testBatchDeleteStrings() {
        ArrayList<String> stringData = new ArrayList<>(
                Arrays.asList("this", "is string test", "good luck", "nice to meet u", "lemaza nhn hona", "hi"));
        assertArrayEquals(new int[] { 6, 0 }, stringTable.batchInsert(stringData,stringData.size()+stringTable.getSize()));
        ArrayList<String> deleteData = new ArrayList<>(
                Arrays.asList("hi", "data structure", "is string test", "hi", "perfect hashing"));
        int[] expectedResults = { 2, 3 };
        assertArrayEquals(expectedResults, stringTable.batchDelete(deleteData));
    }

    @Test
    void testBatchDeleteChars() {
        ArrayList<Character> charData = new ArrayList<>(Arrays.asList('a', 'b', 'c',
        'd', 'e', 'f'));
        assertArrayEquals(new int[] { 6, 0 }, charTable.batchInsert(charData,charData.size()+charTable.getSize()));
        ArrayList<Character> deleteData = new ArrayList<>(Arrays.asList('4', 'd',
        '8', 'z', 'd'));
        int[] expectedResults = { 1, 4 };
        assertArrayEquals(expectedResults, charTable.batchDelete(deleteData));
    }

    @Test
    void testBatchInsertIntegers() {
        ArrayList<Integer> intData = new ArrayList<>(Arrays.asList(1, 5, 111, 4, 6, 2, -2));
        assertArrayEquals(new int[] { 7, 0 }, intTable.batchInsert(intData,intData.size()+intTable.getSize()));
        ArrayList<Integer> insertData = new ArrayList<>(Arrays.asList(3, 2, 11, 3, 0));
        int[] expectedResults = { 3, 2 };
        assertArrayEquals(expectedResults, intTable.batchInsert(insertData,insertData.size()+intTable.getSize()));
    }

    @Test
    void testBatchInsertDoubles() {
        ArrayList<Double> doubleData = new ArrayList<>(Arrays.asList(1.9, -5.6, 111.0, 41213.3, 6.0, 2.2));
        assertArrayEquals(new int[] { 6, 0 }, doubleTable.batchInsert(doubleData,doubleData.size()+doubleTable.getSize()));
        ArrayList<Double> insertData = new ArrayList<>(Arrays.asList(3.0, 2.22, -11.5, 3.0, 0.0));
        int[] expectedResults = { 4, 1 };
        assertArrayEquals(expectedResults, doubleTable.batchInsert(insertData,insertData.size()+doubleTable.getSize()));
    }

    @Test
    void testBatchInsertStrings() {
        ArrayList<String> stringData = new ArrayList<>(
                Arrays.asList("this", "is string test", "good luck", "nice to meet u", "lemaza nhn hona", "hi"));
        assertArrayEquals(new int[] { 6, 0 }, stringTable.batchInsert(stringData,stringData.size()+stringTable.getSize()));
        ArrayList<String> insertData = new ArrayList<>(
                Arrays.asList("hi", "data structure", "is string test", "hi", "perfect hashing"));
        int[] expectedResults = { 2, 3 };
        assertArrayEquals(expectedResults, stringTable.batchInsert(insertData,insertData.size()+stringTable.getSize()));
    }

    @Test
    void testBatchInsertChars() {
        ArrayList<Character> charData = new ArrayList<>(Arrays.asList('a', 'b', 'c',
        'd', 'e', 'f'));
        assertArrayEquals(new int[]{6, 0}, charTable.batchInsert(charData,charData.size()+charTable.getSize()));
        ArrayList<Character> insertData = new ArrayList<>(Arrays.asList('4', 'd',
        '8', 'z', 'd'));
        int[] expectedResults = { 3, 2 };
        assertArrayEquals(expectedResults, charTable.batchInsert(insertData,insertData.size()+charTable.getSize()));
    }

    @Test
    void testDeleteIntegers() {
        ArrayList<Integer> intData = new ArrayList<>(Arrays.asList(1, 5, 111, 4, 6, 2, -2));
        assertArrayEquals(new int[] { 7, 0 }, intTable.batchInsert(intData,intData.size()+intTable.getSize()));
        assertTrue(intTable.delete(111));
        assertFalse(intTable.delete(-9840));
    }

    @Test
    void testDeleteDoubles() {
        ArrayList<Double> doubleData = new ArrayList<>(Arrays.asList(1.9, -5.6, 111.0, 41213.3, 6.0, 2.2));
        assertArrayEquals(new int[] { 6, 0 }, doubleTable.batchInsert(doubleData,doubleData.size()+doubleTable.getSize()));
        assertTrue(doubleTable.delete(1.9));
        assertFalse(doubleTable.delete(-2.22));
    }

    @Test
    void testDeleteStrings() {
        ArrayList<String> stringData = new ArrayList<>(
                Arrays.asList("this", "is string test", "good luck", "nice to meet u", "lemaza nhn hona", "hi"));
        assertArrayEquals(new int[] { 6, 0 }, stringTable.batchInsert(stringData,stringData.size()+stringTable.getSize()));
        assertTrue(stringTable.delete("is string test"));
        assertFalse(stringTable.delete("non-existing"));
    }

    @Test
    void testDeleteChars() {
        ArrayList<Character> charData = new ArrayList<>(Arrays.asList('a', 'b', 'c',
        'd', 'e', 'f'));
        assertArrayEquals(new int[]{6, 0}, charTable.batchInsert(charData,charData.size()+charTable.getSize()));
        assertTrue(charTable.delete('d'));
        assertFalse(charTable.delete('z'));
    }

    @Test
    void testGetCollisions() {

    }

    @Test
    void testGetRebuild() {

    }

    @Test
    void testInsertIntegers() {
        ArrayList<Integer> intData = new ArrayList<>(Arrays.asList(1, 5, 111, 4, 6, 2, -2));
        assertArrayEquals(new int[] { 7, 0 }, intTable.batchInsert(intData,intData.size()+intTable.getSize()));
        assertTrue(intTable.insert(-33));
        assertFalse(intTable.insert(111));
    }

    @Test
    void testInsertDoubles() {
        ArrayList<Double> doubleData = new ArrayList<>(Arrays.asList(1.9, -5.6, 111.0, 41213.3, 6.0, 2.2));
        assertArrayEquals(new int[] { 6, 0 }, doubleTable.batchInsert(doubleData,doubleData.size()+doubleTable.getSize()));
        assertTrue(doubleTable.insert(-3.0));
        assertFalse(doubleTable.insert(111.0));
    }

    @Test
    void testInsertStrings() {
        ArrayList<String> stringData = new ArrayList<>(
                Arrays.asList("this", "is string test", "good luck", "nice to meet u", "lemaza nhn hona", "hi"));
        assertArrayEquals(new int[] { 6, 0 }, stringTable.batchInsert(stringData,stringData.size()+stringTable.getSize()));
        assertTrue(stringTable.insert("new string"));
        assertFalse(stringTable.insert("is string test"));
    }

    @Test
    void testInsertChars() {
        ArrayList<Character> charData = new ArrayList<>(Arrays.asList('a', 'b', 'c',
        'd', 'e', 'f'));
        assertArrayEquals(new int[]{6, 0}, charTable.batchInsert(charData,charData.size()+charTable.getSize()));
        assertTrue(charTable.insert('x'));
        assertFalse(charTable.insert('d'));
    }

    @Test
    void testSearchForKeyRandomIntegers() {
        ArrayList<Integer> intData = new ArrayList<>();
        Random random_method = new Random();
        // loop for generation random number
        for (int i = 0; i < MAX_ARRAY_SIZE; i++) {
            int element = random_method.nextInt(Integer.MIN_VALUE,Integer.MAX_VALUE);
            intData.add(element);
        }
        HashSet<Object> hs = new HashSet<>(Arrays.asList(intData));
        assertEquals(hs.contains(random_method.nextInt(Integer.MAX_VALUE)), intTable.searchForKey(2));
    }

    @Test
    void testSearchForKeyRandomStrings() {
        ArrayList<String> stringData = new ArrayList<>();
        Random random_method = new Random();
        // loop for generation random Strings
        for (int i = 0; i < MAX_ARRAY_SIZE; i++) {
            byte[] array = new byte[random_method.nextInt(MAX_STRING_SIZE)];
            new Random().nextBytes(array);
            String element = new String(array, Charset.forName("UTF-8"));
            stringData.add(element);
        }
        HashSet<Object> hs = new HashSet<>(Arrays.asList(stringData));
        assertEquals(hs.contains(random_method.nextInt(Integer.MAX_VALUE)), intTable.searchForKey(2));
    }

    // Mean time analysis cases
    @Test
    void testSearchForKeyIntegers() {
        ArrayList<Integer> intData = new ArrayList<>(Arrays.asList(1, 111, 4, 6, 2, -2));
        assertArrayEquals(new int[] { 6, 0 }, intTable.batchInsert(intData,intData.size()+intTable.getSize()));
        assertTrue(intTable.searchForKey(2));
        assertFalse(intTable.searchForKey(-33));
    }

    @Test
    void testSearchForKeyPositiveIntegers() {
        ArrayList<Integer> intData = new ArrayList<>(Arrays.asList(0, 19, 43, 60, 2, 4));
        assertArrayEquals(new int[] { 6, 0 }, intTable.batchInsert(intData,intData.size()+intTable.getSize()));
        assertTrue(intTable.searchForKey(2));
        assertFalse(intTable.searchForKey(3));
    }

    @Test
    void testSearchForKeyNegativeIntegers() {
        ArrayList<Integer> intData = new ArrayList<>(Arrays.asList(-1, -111, -4, -6, -2, -54759));
        assertArrayEquals(new int[] { 6, 0 }, intTable.batchInsert(intData,intData.size()+intTable.getSize()));
        assertTrue(intTable.searchForKey(-2));
        assertFalse(intTable.searchForKey(-33));
    }

    @Test
    void testSearchForKeyDoubles() {
        ArrayList<Double> doubleData = new ArrayList<>(Arrays.asList(1.9, -5.6, 111.0, 41213.3, 6.0, 2.2));
        assertArrayEquals(new int[] { 6, 0 }, doubleTable.batchInsert(doubleData,doubleData.size()+doubleTable.getSize()));
        assertTrue(doubleTable.searchForKey(-5.6));
        assertFalse(doubleTable.searchForKey(111564.0));
    }

    @Test
    void testSearchForKeyPositveDoubles() {
        ArrayList<Double> doubleData = new ArrayList<>(Arrays.asList(1.9, 9.343, 19.4, 809.3, 99.0, 2.2));
        assertArrayEquals(new int[] { 6, 0 }, doubleTable.batchInsert(doubleData,doubleData.size()+doubleTable.getSize()));
        assertTrue(doubleTable.searchForKey(19.4));
        assertFalse(doubleTable.searchForKey(1164.0));
    }

    @Test
    void testSearchForKeyNegativeDoubles() {
        ArrayList<Double> doubleData = new ArrayList<>(Arrays.asList(-1.9, -5.6, -111.0, -41213.3, -6.0, -2.2));
        assertArrayEquals(new int[] { 6, 0 }, doubleTable.batchInsert(doubleData,doubleData.size()+doubleTable.getSize()));
        assertTrue(doubleTable.searchForKey(-5.6));
        assertFalse(doubleTable.searchForKey(111564.543));
    }

    @Test
    void testSearchForKeyStrings() {
        ArrayList<String> stringData = new ArrayList<>(
                Arrays.asList("data", "structure", "perfect hashing good luck", "nice to meet u", "3ash", "hi"));
        assertArrayEquals(new int[] { 6, 0 }, stringTable.batchInsert(stringData,stringData.size()+stringTable.getSize()));
        assertTrue(stringTable.searchForKey("hi"));
        assertFalse(stringTable.searchForKey("non-existing"));
    }

    @Test
    void testSearchForKeyChars() {
        ArrayList<Character> charData = new ArrayList<>(Arrays.asList('a', 'b', 'c',
        'd', 'e', 'f'));
        assertArrayEquals(new int[]{6, 0}, charTable.batchInsert(charData,charData.size()+charTable.getSize()));
        assertTrue(charTable.searchForKey('d'));
        assertFalse(charTable.searchForKey('z'));
        System.out.println(charTable.getCollisions());
        System.out.println(charTable.getRebuild());
    }

}

