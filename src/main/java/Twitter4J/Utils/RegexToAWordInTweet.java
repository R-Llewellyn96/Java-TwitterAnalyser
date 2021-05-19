package Twitter4J.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexToAWordInTweet {

    /**
     * This method takes two arguments, the word to find and the sentence to find it in.
     *
     * @author Ryan Llewellyn
     * @param wordToFind The word to find in a sentence.
     * @param inSentence The sentence you wish to find the word in.
     * @return trueOrFalse True if the word is present in the sentence, false if the word is absent.
     */
    public static boolean regexToAWordInTweet(String wordToFind, String inSentence) {

        // Get exact word, regex finds exact word with space either side
        String regexWord = String.format("(?i).*?\\b%s\\b.*?", wordToFind);

        // Word to find
        Pattern pattern = Pattern.compile(regexWord, Pattern.CASE_INSENSITIVE);

        // Sentence to search in
        Matcher matcher = pattern.matcher(inSentence);

        // true if found, false if not found, return to method caller
        return matcher.find();
    }
}
