package Twitter4J.Utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CheckTweetListAgainstWordListParallel {

    /**
     * This method takes a list of tweets and checks each tweet against a problematic words list, if any problematic
     * words are found in the tweet text, the tweet will be added to a new list of tweets, this list of problematic
     * tweets will be returned to the method caller, if no tweets are found the returned list will be empty.
     *
     * @param tweetList The list of tweets to be checked.
     * @param wordList The problematic words list to check tweet content against.
     * @return problematicTweets The list of tweets containing problematic words.
     */
    public static List<TweetWithSentiment> checkTweetListAgainstWordlistParallel(List<TweetWithSentiment> tweetList, String[] wordList) {

        // List of Tweets to be returned to caller
        List<TweetWithSentiment> problematicTweets = new ArrayList<>();

        // Loop through each tweet in the passed list of tweets
        // Parallelization of forEach loop
        tweetList.parallelStream().forEach(tweet -> {

            // Initialise boolean to check whether a tweet is contained in list
            boolean alreadyContainedInList = false;

            // Loop through prohibited words list, if any tweets contain prohibited word, increment count
            for (String problematicWord : wordList) {
                if (RegexToAWordInTweet.regexToAWordInTweet(problematicWord, tweet.tweetText)) {

                    // check if we have saved any problem tweets, if not just add tweet to the list
                    if (problematicTweets.size() > 0) {

                        // loop through problem tweets, check if tweet id is already present,
                        // if it is, then dont add it again
                        for (TweetWithSentiment checkTweet : problematicTweets) {

                            // Gets each tweet in the problematic tweets list,
                            // used for checking if tweet is already in list
                            // Check whether the current tweets id is
                            // already in the problematic tweet list,
                            // if it is then flag is set to true, if not it will remain false
                            if (tweet.id.equals(checkTweet.id)) {
                                alreadyContainedInList = true;
                                break;
                            }
                        }

                        // if the tweet isn't already in the list ,add it
                        if (!alreadyContainedInList) {
                            problematicTweets.add(tweet);
                        }

                        // after checking if it's in list, reset flag for next comparison
                        alreadyContainedInList = false;

                        // If we haven't saved any tweets yet, just save it
                    } else {
                        problematicTweets.add(tweet);
                    }
                }
            }
        });

        // Sort list & return sorted list to method caller
        problematicTweets.sort(Comparator.comparing(TweetWithSentiment::getId).reversed());
        return problematicTweets;
    }
}
