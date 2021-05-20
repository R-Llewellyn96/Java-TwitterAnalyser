import Twitter4J.GetUserId;
import Twitter4J.Utils.*;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.Arrays;
import java.util.List;

import static Twitter4J.GetTweetSentimentParallel.*;
import static Twitter4J.GetUsersTweets.*;
import static Twitter4J.Utils.CheckTweetListAgainstWordList.checkTweetListAgainstWordlist;
import static Twitter4J.Utils.ConvertTweetListIntoCSV.*;
import static Twitter4J.Utils.GetStringsFromFile.*;

public class Main {
    public static void main(String[] args) {

        // Get twitter api enpoints for "users" or "tweets", commented out as its for dev purposes
        //GetTwitterAPIEndpoints.getEndpointsForFamily("users");

        // Check whether main has been called with an argument or not
        if (Arrays.stream(args).findFirst().isPresent()) {

            // Get the passed argument and initialise usernameToSearch
            String usernameToSearch = Arrays.stream(args).findFirst().get().trim();

            if (usernameToSearch.startsWith("@")) {
                usernameToSearch = usernameToSearch.substring(1);
            }

        try {
            // Get access codes from files
            String consumerKey = getStringsFromFile("access_codes/consumer_key.txt").get(0).trim();
            String consumerSecret = getStringsFromFile("access_codes/consumer_secret.txt").get(0).trim();

            // Try to create twitter object
            try {
            // authenticate using access codes from twitter developer account
            // Get twitter API instance
            Twitter twitter = GetTwitterObject.getTwitter(consumerKey, consumerSecret);

            // Call getUserId method and pass the username,
            // will return userId which can be used to gather tweets
            Long userId = GetUserId.getUserId(usernameToSearch, twitter);

            // check that a userId was found, else tell user no account found.
            if (userId != null) {

                System.out.println("\nGetting Tweets, for user @" + usernameToSearch + " please stand by...\n");

                    // call method to get user tweets, returns a list of tweets
                    List<Tweet> tweetList = getUserTweets(userId, twitter);

                    long startTime = System.currentTimeMillis();

                    // Add sentiment to Tweets
                    List<TweetWithSentiment> tweetWithSentimentList = getTweetSentimentParallel(tweetList);

                    long endTime = System.currentTimeMillis();
                    System.out.println("That took " + (endTime - startTime) + " milliseconds");

                    // Check whether returned list is empty or not
                    if (!tweetList.isEmpty()) {

                        System.out.println("Tweets gathered for user: @" + usernameToSearch);
                        System.out.println("Total in Tweet List: " + tweetList.size());
                        System.out.println("----Processing Tweets----");

                        // Get list of prohibited words from file
                        List<String> politicalWords = getStringsFromFile("political-words.txt");

                        // Get list of prohibited words from file
                        List<String> racistWords = getStringsFromFile("racist-words.txt");

                        // Get list of prohibited words from file
                        List<String> swearWords = getStringsFromFile("swear-words.txt");

                        // Get list of political tweets by filtering tweet list against word list
                        // and extracting tweets containing political words
                        List<TweetWithSentiment> politicalTweets = checkTweetListAgainstWordlist(tweetWithSentimentList, politicalWords);

                        // Get list of political tweets by filtering tweet list against word list
                        // and extracting tweets containing political words
                        List<TweetWithSentiment> racistTweets = checkTweetListAgainstWordlist(tweetWithSentimentList, racistWords);

                        // Get list of political tweets by filtering tweet list against word list
                        // and extracting tweets containing political words
                        List<TweetWithSentiment> swearTweets = checkTweetListAgainstWordlist(tweetWithSentimentList, swearWords);

                        System.out.println("Tweets Processed : " + tweetWithSentimentList.size());
                        System.out.println("Tweets with political words : " + politicalTweets.size());
                        System.out.println("Tweets with racist words : " + racistTweets.size());
                        System.out.println("Tweets with swear words : " + swearTweets.size());

                        // Save politicalTweets to CSV
                        if (convertTweetListIntoCSV(
                                politicalTweets,
                                usernameToSearch,
                                "political",
                                "UTF-8")) {
                            System.out.println("CSV Generation Successful!");
                        } else {
                            System.out.println("CSV Generation Failed!");
                        }

                        // Save racistTweets to CSV
                        if (convertTweetListIntoCSV(
                                racistTweets,
                                usernameToSearch,
                                "racist",
                                "UTF-8")) {
                            System.out.println("CSV Generation Successful!");
                        } else {
                            System.out.println("CSV Generation Failed!");
                        }

                        // Save politicalTweets to CSV
                        if (convertTweetListIntoCSV(
                                swearTweets,
                                usernameToSearch,
                                "swearing",
                                "UTF-8")) {
                            System.out.println("CSV Generation Successful!");
                        } else {
                            System.out.println("CSV Generation Failed!");
                        }

                        // If list of tweets is empty tell user no tweets found
                    } else {
                        System.out.println("No tweets found for user : @" + usernameToSearch);
                    }

                    // If user couldn't be found, tell user
            } else {
                System.out.println("Error no UserID could be found for user @" + usernameToSearch);
            }

            // Catch any error with the Twitter API
            } catch (TwitterException e) {
                System.out.println("Error in the Twitter API unable to gather tweets for user : @" + usernameToSearch);
                System.out.println("This may be a Twitter API problem,\nPlease check your access codes or try again later.");
            }

            // Catch file not found exception when reading access codes
        } catch (Exception e) {
            System.out.println("Access Codes could not be found, or were invalid: " + e.getCause());
        }


            // If no username entered terminate program
        } else {
            System.out.println("No username entered");
            System.exit(0);
        }
    }
}
