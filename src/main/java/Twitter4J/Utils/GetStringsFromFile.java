package Twitter4J.Utils;

import twitter4j.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GetStringsFromFile {

    /**
     * This method parses each line of a text file containing words, adds each word to an ArrayList,
     * for future use, for example in comparisons to twitter status content,
     * to flag problematic social media content, or to grab authentication credentials.
     *
     * @author Ryan Llewellyn
     * @return wordList The ArrayList of Strings from the bad-words.txt file, containing offensive words.
     */
    public static List<String> getStringsFromFile(String filename) {

        // URL to profanity text document, containing swear words
        String fileURI = "src/main/java/" + filename;

        // Initialise ArrayList,
        // we will convert lines in the text file
        // into an ArrayList, which will be turned into a JSON array for use in comparisons to tweet content later
        List<String> wordList = new ArrayList<>();

        // Create instance of BufferedReader object
        BufferedReader bufferedReader;

        // Will catch any BufferedReader errors or File not found errors
        try {
            bufferedReader = new BufferedReader(new FileReader(fileURI));

            // Initialise String line for each line in the txt file
            String line = bufferedReader.readLine();

            // loop through each line in the file until line is null,
            // convert all lines to arraylist and then to JSON
            while (line != null) {

                // Add line to Array List
                wordList.add(line);

                // Read next line, (.readLine() auto-increments to next line)
                line = bufferedReader.readLine();
            }

            // Close BufferedReader object once all lines have been read from file
            bufferedReader.close();

            // Catch exception of BufferedReader error or File not found
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return list to method caller, either full or null,
        // depending on whether text file is present
        return wordList;
    }

    /**
     * This method converts the passed ArrayList into a JSON Array object
     *
     * @param list An ArrayList of Strings for conversion into a JSON Array object
     * @return JSONArray list created from the passed list parameter object
     */
    public static JSONArray convertStringListToJsonArray(List<String> list) {
        return new JSONArray(list);
    }
}
