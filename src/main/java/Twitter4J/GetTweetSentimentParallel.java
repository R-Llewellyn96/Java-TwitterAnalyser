package Twitter4J;

import Twitter4J.Utils.Tweet;
import Twitter4J.Utils.TweetWithSentiment;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static SentimentAnalysis.GetSentiment.getSentimentScore;

public class GetTweetSentimentParallel {

    /**
     * This method takes a List of Tweets and performs Sentiment Analysis using Stanford's NLP library,
     * in a parallelized format.
     * @param tweetList The list of tweets to be processed
     * @return tweetWithSentimentList The list of processed tweets with sentiment scores
     */
    public static List<TweetWithSentiment> getTweetSentiment(List<Tweet> tweetList) {

        // Initialise a LinkedList of tweets with a sentiment score
        List<TweetWithSentiment> tweetWithSentimentList = new LinkedList<>();

        // Parallelization of forEach loop
        tweetList.parallelStream().forEach(tweet -> {

            // Create new Tweet object containing values from API and,
            // Add processed tweet to the tweetWithSentimentList
            tweetWithSentimentList.add(new TweetWithSentiment(
                    tweet.id,
                    tweet.createdAt,
                    tweet.username,
                    tweet.displayName,
                    tweet.tweetText,
                    String.valueOf(getSentimentScore(tweet.tweetText))));

        });

        // Sort list
        tweetWithSentimentList.sort(Comparator.comparing(TweetWithSentiment::getId).reversed());

        // Return the list of tweets with sentiment scores to the caller
        return tweetWithSentimentList;
    }
}
