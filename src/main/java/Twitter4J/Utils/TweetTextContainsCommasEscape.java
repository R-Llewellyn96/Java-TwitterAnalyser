package Twitter4J.Utils;

public class TweetTextContainsCommasEscape {

    /**
     * This method takes tweetText as an input and escapes the string allowing for use of commas in the tweet,
     * without affecting CSV file generation.
     *
     * @param tweetText String of characters, the Text of a tweet.
     * @return EscapedTweetText The input String, escaped double speech marks, allows for comma content in CSV file.
     */
    public static String escapeCommas(String tweetText) {
        return "\"" + tweetText + "\"";
    }
}
