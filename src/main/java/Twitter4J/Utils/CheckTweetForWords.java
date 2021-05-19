package Twitter4J.Utils;

import java.util.List;

public class CheckTweetForWords {

    /**
     * This method checks tweet content for a match with a banned keyword in a word list.
     *
     * @author Ryan Llewellyn
     * @param tweet The tweet to be checked.
     * @return trueOrFalse Dependent on whether the tweet contains a word in the wordlist.
     */
    public static Boolean checkTweetForWords(Tweet tweet) {

        // Initialise the content of the passed tweet to a String object
        String tweetContent = tweet.tweetText;

        // Get arraylist of banned words for comparison to tweet content
        List<String> bannedWords = GetStringsFromFile.getStringsFromFile("political-words.txt");

        // Loop through each word in the banned word list, and return true if theres a match
        for (String word : bannedWords) {

            // If a banned word is present in the tweet content, then return true to the caller
            if (RegexToAWordInTweet.regexToAWordInTweet(word, tweetContent)) {
                return true;
            }
        }

        // If the loop finished then that means no matches were found, therefore return false
        return false;
    }
}
