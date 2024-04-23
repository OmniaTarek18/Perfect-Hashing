import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnglishDictionaryTest {

    private EnglishDictionary dictL = new EnglishDictionary("linear");
    private EnglishDictionary dictQ = new EnglishDictionary("quadratic");
    ArrayList<String> dictionaryData = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        dictionaryData.add("apple");
        dictionaryData.add("banana");
        dictionaryData.add("cat");
        dictionaryData.add("dog");
        dictionaryData.add("elephant");
        dictionaryData.add("fish");
        dictionaryData.add("gorilla");
        dictionaryData.add("horse");
        dictionaryData.add("iguana");
        dictionaryData.add("jaguar");
        dictionaryData.add("kangaroo");
        dictionaryData.add("lion");
        dictionaryData.add("monkey");
        dictionaryData.add("newt");
        dictionaryData.add("ostrich");
        dictionaryData.add("penguin");
        dictionaryData.add("quail");
        dictionaryData.add("rhinoceros");
        dictionaryData.add("snake");
        dictionaryData.add("tiger");
        dictionaryData.add("unicorn");
        dictionaryData.add("vulture");
        dictionaryData.add("walrus");
        dictionaryData.add("xenops");
        dictionaryData.add("yak");
        dictionaryData.add("zebra");
    }

    @Test
    void testBatchInsertL() {
        assertArrayEquals(new int[]{dictionaryData.size(), 0}, dictL.batchInsert(dictionaryData));

        ArrayList<String> insertData = new ArrayList<>();
        insertData.add("cat");
        insertData.add("butterfly");
        insertData.add("cheetah");
        insertData.add("dog");
        insertData.add("eagle");
        insertData.add("eagle");
        int[] expectedResultsL = { 3, 3 };
        assertArrayEquals(expectedResultsL, dictL.batchInsert(insertData));
    }

    @Test
    void testBatchInsertQ() {
        assertArrayEquals(new int[]{dictionaryData.size(), 0}, dictQ.batchInsert(dictionaryData));

        ArrayList<String> insertData = new ArrayList<>();
        insertData.add("cat");
        insertData.add("butterfly");
        insertData.add("cheetah");
        insertData.add("dog");
        insertData.add("eagle");
        insertData.add("eagle");
        int[] expectedResultsQ = { 3, 3 };
        assertArrayEquals(expectedResultsQ, dictQ.batchInsert(insertData));
    }

    @Test
    void testBatchDeleteL() {
        assertArrayEquals(new int[]{dictionaryData.size(), 0}, dictL.batchInsert(dictionaryData));
        ArrayList<String> deleteData = new ArrayList<>();
        deleteData.add("cat");
        deleteData.add("butterfly");
        deleteData.add("cheetah");
        deleteData.add("dog");
        deleteData.add("eagle");
        deleteData.add("dog");
        int[] expectedResultsL = { 2, 4 };
        assertArrayEquals(expectedResultsL, dictL.batchDelete(deleteData));
    }

    @Test
    void testBatchDeleteQ() {
        assertArrayEquals(new int[]{dictionaryData.size(), 0}, dictQ.batchInsert(dictionaryData));
        ArrayList<String> deleteData = new ArrayList<>();
        deleteData.add("cat");
        deleteData.add("butterfly");
        deleteData.add("cheetah");
        deleteData.add("dog");
        deleteData.add("eagle");
        deleteData.add("dog");
        int[] expectedResultsQ = { 2, 4 };
        assertArrayEquals(expectedResultsQ, dictQ.batchDelete(deleteData));
    }


    @Test
    void testBatchDeleteTheDictL() {
        assertArrayEquals(new int[]{dictionaryData.size(), 0}, dictL.batchInsert(dictionaryData));
        assertArrayEquals(new int[]{dictionaryData.size(), 0}, dictL.batchDelete(dictionaryData));
    }

    @Test
    void testBatchDeleteTheDictQ() {
        assertArrayEquals(new int[]{dictionaryData.size(), 0}, dictQ.batchInsert(dictionaryData));
        assertArrayEquals(new int[]{dictionaryData.size(), 0}, dictQ.batchDelete(dictionaryData));
    }

    @Test
    void testDeleteL() {
        assertArrayEquals(new int[]{dictionaryData.size(), 0}, dictL.batchInsert(dictionaryData));
        assertTrue(dictL.delete("lion"));
        assertFalse(dictL.delete("antelope"));
    }

    @Test
    void testDeleteQ() {
        assertArrayEquals(new int[]{dictionaryData.size(), 0}, dictQ.batchInsert(dictionaryData));
        assertTrue(dictQ.delete("lion"));
        assertFalse(dictQ.delete("antelope"));
    }

    @Test
    void testInsertL() {
        assertArrayEquals(new int[]{dictionaryData.size(), 0}, dictL.batchInsert(dictionaryData));
        assertTrue(dictL.insert("antelope"));
        assertFalse(dictL.insert("lion"));
    }

    @Test
    void testInsertQ() {
        assertArrayEquals(new int[]{dictionaryData.size(), 0}, dictQ.batchInsert(dictionaryData));
        assertTrue(dictQ.insert("antelope"));
        assertFalse(dictQ.insert("lion"));
    }

    @Test
    void testSearchL() {
        assertArrayEquals(new int[]{dictionaryData.size(), 0}, dictL.batchInsert(dictionaryData));
        assertTrue(dictL.search("lion"));
        assertFalse(dictL.search("eel"));
    }

    @Test
    void testSearchQ() {
        assertArrayEquals(new int[]{dictionaryData.size(), 0}, dictQ.batchInsert(dictionaryData));
        assertTrue(dictQ.search("lion"));
        assertFalse(dictQ.search("eel"));
    }
}
