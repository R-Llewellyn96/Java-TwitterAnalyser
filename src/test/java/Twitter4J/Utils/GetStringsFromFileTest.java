package Twitter4J.Utils;

import org.junit.jupiter.api.Test;
import twitter4j.JSONArray;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetStringsFromFileTest {

    @Test
    void getStringsFromFile() {
        try {
            String wordList = "political-words.txt";
            String[] profanityList = GetStringsFromFile.getStringsFromFile(wordList);
            assertNotNull(profanityList);
            assertTrue(profanityList.length > 0);
            Object returnedWords = GetStringsFromFile.getStringsFromFile(wordList);
            assertTrue(returnedWords instanceof String[]);
            System.out.println("Get words from File: PASSED");
        } catch (Exception e) {
            System.out.println("Get words from File: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void convertStringListToJsonArray() {
        try {
            List<String> testingArrayListString = new ArrayList<>();
            testingArrayListString.add("TestString");
            testingArrayListString.add("123");
            JSONArray returnedArray = GetStringsFromFile.convertStringListToJsonArray(testingArrayListString);
            assertNotNull(returnedArray);
            assertTrue(returnedArray.length() > 0);
            assertEquals(testingArrayListString.size(), returnedArray.length());
            Object returnedArrayObject = GetStringsFromFile.convertStringListToJsonArray(testingArrayListString);
            assertTrue(returnedArrayObject instanceof JSONArray);
            System.out.println("Convert List to JSON Array: PASSED");
        } catch (Exception e) {
            System.out.println("Convert List to JSON Array: FAILED");
            fail("Exception: " + e);
        }
    }
}