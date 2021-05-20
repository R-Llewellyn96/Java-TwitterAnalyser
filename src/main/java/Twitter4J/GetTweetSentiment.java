package Twitter4J;

import Twitter4J.Utils.Tweet;
import Twitter4J.Utils.TweetWithSentiment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static SentimentAnalysis.GetSentiment.getSentimentScore;

public class GetTweetSentiment {

    /**
     * This method takes a List of Tweets and performs Sentiment Analysis using Stanford's NLP library
     * @param tweetList The list of tweets to be processed
     * @return tweetWithSentimentList The list of processed tweets with sentiment scores
     */
    public static List<TweetWithSentiment> getTweetSentiment(List<Tweet> tweetList) {

        // Initialise a ArrayList of tweets with a sentiment score
        List<TweetWithSentiment> tweetWithSentimentList = new ArrayList<>();

        // Initialise sentiment score, to check whether to add tweet
        int sentimentScore;

        // Loop through all statuses from twitter user timeline
        for (Tweet tweet : tweetList) {

            // Get sentiment of tweet using Stanford NLP
            sentimentScore = getSentimentScore(tweet.tweetText);

            // If the sentiment score is between 0 - 4 that means ML has worked
            // and we have a sentiment value, so store it in the tweetList,
            // if false, just go to the next tweet
            if (sentimentScore < 5) {

                // Create new Tweet object containing values from API
                TweetWithSentiment tweetWithSentiment = new TweetWithSentiment(
                        tweet.id,
                        tweet.createdAt,
                        tweet.username,
                        tweet.displayName,
                        tweet.tweetText,
                        String.valueOf(sentimentScore)
                );

                // Add processed tweet to the tweetWithSentimentList
                tweetWithSentimentList.add(tweetWithSentiment);
            }
        }

        // Sort list
        tweetWithSentimentList.sort(Comparator.comparing(TweetWithSentiment::getId).reversed());

        // Return the list of tweets with sentiment scores to the caller
        return tweetWithSentimentList;
    }
}
