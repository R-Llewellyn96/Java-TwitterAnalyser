package Twitter4J.Utils;

/**
 * Create Tweet object, containing values from a tweet, from the Twitter API,
 * with the addition of a sentiment score, given from Sentiment Analysis from Stanford NLP library.
 *
 * @author Ryan Llewellyn
 */
public class TweetWithSentiment extends Tweet{

    public String sentimentScore;

    public TweetWithSentiment(String id, String createdAt, String username, String displayName, String tweetText, String sentimentScore) {
        super(id, createdAt, username, displayName, tweetText);
        this.sentimentScore = sentimentScore;
    }


}
