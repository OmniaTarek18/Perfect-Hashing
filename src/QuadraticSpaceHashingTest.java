import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuadraticSpaceHashingTest {
    private QuadraticSpaceHashing<Integer> intTable = new QuadraticSpaceHashing<>(1);
    private QuadraticSpaceHashing<String> stringTable = new QuadraticSpaceHashing<>(1);
    private QuadraticSpaceHashing<Double> doubleTable = new QuadraticSpaceHashing<>(1);
    private QuadraticSpaceHashing<Character> charTable = new QuadraticSpaceHashing<>(1);

    @BeforeEach
    public void setUp() {
        // Batch insert data for each test
        ArrayList<Integer> intData = new ArrayList<>(Arrays.asList(1, 5, 111, 4, 6, 2, -2));
        intTable.batchInsert(intData);

        ArrayList<Double> doubleData = new ArrayList<>(Arrays.asList(1.9, -5.6, 111.0, 41213.3, 6.0, 2.2));
        doubleTable.batchInsert(doubleData);

        ArrayList<String> stringData = new ArrayList<>(
                Arrays.asList("this", "is string test", "good luck", "nice to meet u", "lemaza nhn hona", "hi"));
        stringTable.batchInsert(stringData);

        // ArrayList<Character> charData = new ArrayList<>(Arrays.asList('a', 'b', 'c',
        // 'd', 'e', 'f'));
        // charTable.batchInsert(charData);
    }

    @Test
    void testBatchDeleteIntegers() {
        ArrayList<Integer> deleteData = new ArrayList<>(Arrays.asList(3, 2, 11, 2, 0));
        int[] expectedResults = { 1, 4 };
        assertArrayEquals(expectedResults, intTable.batchDelete(deleteData));
    }

    @Test
    void testBatchDeleteDoubles() {
        ArrayList<Double> deleteData = new ArrayList<>(Arrays.asList(3.0, 2.22, -11.5, -5.6, 0.0));
        int[] expectedResults = { 1, 4 };
        assertArrayEquals(expectedResults, doubleTable.batchDelete(deleteData));
    }

    @Test
    void testBatchDeleteStrings() {
        ArrayList<String> deleteData = new ArrayList<>(
                Arrays.asList("hi", "data structure", "is string test", "hi", "perfect hashing"));
        int[] expectedResults = { 2, 3 };
        assertArrayEquals(expectedResults, stringTable.batchDelete(deleteData));
    }

    @Test
    void testBatchDeleteChars() {
        // ArrayList<Character> deleteData = new ArrayList<>(Arrays.asList('4', 'd',
        // '8', 'z', 'd'));
        // int[] expectedResults = { 1, 4 };
        // assertArrayEquals(expectedResults, charTable.batchDelete(deleteData));
    }

    @Test
    void testBatchInsertIntegers() {
        ArrayList<Integer> insertData = new ArrayList<>(Arrays.asList(3, 2, 11, 3, 0));
        int[] expectedResults = { 3, 2 };
        assertArrayEquals(expectedResults, intTable.batchInsert(insertData));
    }

    @Test
    void testBatchInsertDoubles() {
        ArrayList<Double> insertData = new ArrayList<>(Arrays.asList(3.0, 2.22, -11.5, 3.0, 0.0));
        int[] expectedResults = { 4, 1 };
        assertArrayEquals(expectedResults, doubleTable.batchInsert(insertData));
    }

    @Test
    void testBatchInsertStrings() {
        ArrayList<String> insertData = new ArrayList<>(
                Arrays.asList("hi", "data structure", "is string test", "hi", "perfect hashing"));
        int[] expectedResults = { 2, 3 };
        assertArrayEquals(expectedResults, stringTable.batchInsert(insertData));
    }

    @Test
    void testBatchInsertChars() {
        // ArrayList<Character> insertData = new ArrayList<>(Arrays.asList('4', 'd',
        // '8', 'z', 'd'));
        // int[] expectedResults = { 4, 1 };
        // assertArrayEquals(expectedResults, charTable.batchInsert(insertData));
    }

    @Test
    void testDeleteIntegers() {
        assertTrue(intTable.delete(111));
        assertFalse(intTable.delete(-9840));
    }

    @Test
    void testDeleteDoubles() {
        assertTrue(doubleTable.delete(1.9));
        assertFalse(doubleTable.delete(-2.22));
    }

    @Test
    void testDeleteStrings() {
        assertTrue(stringTable.delete("is string test"));
        assertFalse(stringTable.delete("non-existing"));
    }

    @Test
    void testDeleteChars() {
        // assertTrue(charTable.delete('d'));
        // assertFalse(charTable.delete('z'));
    }

    @Test
    void testGetCollisions() {

    }

    @Test
    void testGetRebuild() {

    }

    @Test
    void testInsertIntegers() {
        assertTrue(intTable.insert(-33));
        assertFalse(intTable.insert(111));
    }

    @Test
    void testInsertDoubles() {
        assertTrue(doubleTable.insert(-3.0));
        assertFalse(doubleTable.insert(111.0));
    }

    @Test
    void testInsertStrings() {
        assertTrue(stringTable.insert("new string"));
        assertFalse(stringTable.insert("is string test"));
    }

    @Test
    void testInsertChars() {
        // assertTrue(charTable.insert('x'));
        // assertFalse(charTable.insert('d'));
    }

    @Test
    void testSearchForKeyIntegers() {
        assertTrue(intTable.searchForKey(2));
        assertFalse(intTable.searchForKey(-33));
    }

    @Test
    void testSearchForKeyDoubles() {
        assertTrue(doubleTable.searchForKey(-5.6));
        assertFalse(doubleTable.searchForKey(111564.0));
    }

    @Test
    void testSearchForKeyStrings() {
        assertTrue(stringTable.searchForKey("hi"));
        assertFalse(stringTable.searchForKey("non-existing"));
    }

    @Test
    void testSearchForKeyChars() {
        // assertTrue(charTable.searchForKey('d'));
        // assertFalse(charTable.searchForKey('z'));
    }

}
