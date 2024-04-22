import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnglishDictionaryTest {

    private EnglishDictionary dictL = new EnglishDictionary("linear");
    private EnglishDictionary dictQ = new EnglishDictionary("quadratic");
    private ArrayList<String> data = new ArrayList<>();
    
    @BeforeEach
    public void setUp() {
        data.clear();
        data.add("aaa");
        data.add("bb");
        data.add("dddd");
        data.add("aaa");
        data.add("eee");
        data.add("aaa");
    }

    @Test
    void testBatchInsertL() {    
        int[] expectedResults = {4, 2};
        assertArrayEquals(expectedResults,dictL.batchInsert(data));
    }

    @Test
    void testBatchInsertQ() {
        int[] expectedResults = {4, 2};
        assertArrayEquals(expectedResults,dictQ.batchInsert(data));
    }


    @Test
    void testBatchDeleteL() {
        dictL.batchInsert(data); 
        ArrayList<String> deleteData = new ArrayList<>();
        deleteData.add("aaa");
        deleteData.add("bb");
        deleteData.add("bb");
        deleteData.add("bb");
        deleteData.add("bb");
        int[] expectedResultsL = {2 ,3};
        assertArrayEquals(expectedResultsL,dictL.batchDelete(deleteData));
    }


    @Test
    void testBatchDeleteQ() {
        dictQ.batchInsert(data);

        ArrayList<String> deleteData = new ArrayList<>();
        deleteData.add("aaa");
        deleteData.add("bb");
        deleteData.add("bb");
        deleteData.add("bb");
        deleteData.add("bb");
        int[] expectedResultsQ = {2 ,3};
       
        assertArrayEquals(expectedResultsQ,dictQ.batchDelete(deleteData));
    }

    @Test
    void testDeleteL() {
        dictL.batchInsert(data);
        assertTrue(dictL.delete("aaa"));
        assertFalse(dictL.delete("dd"));
    }

    @Test
    void testDeleteQ() {
        dictQ.batchInsert(data);
        assertTrue(dictQ.delete("aaa"));
        assertFalse(dictQ.delete("dd"));
    }

    @Test
    void testInsertL() {
        dictL.batchInsert(data);
        assertTrue(dictL.insert("aa"));
        assertFalse(dictL.insert("dddd"));
    }

    @Test
    void testInsertQ() {
        dictQ.batchInsert(data);
        assertTrue(dictQ.insert("aac"));
        assertFalse(dictQ.insert("dddd"));
    }

    @Test
    void testSearchL() {
        dictL.batchInsert(data);
        assertTrue(dictL.search("aaa"));
        assertFalse(dictL.search("d"));
    }

    @Test
    void testSearchQ() {
        dictQ.batchInsert(data);
        assertTrue(dictQ.search("aaa"));
        assertFalse(dictQ.search("d"));
    }
}
