package Twitter4J;

import Twitter4J.Utils.Tweet;
import Twitter4J.Utils.TwitterCheckNeedToSleep;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;

import static Twitter4J.Utils.Endpoints.USERS_SEARCHFAMILY;
import static Twitter4J.Utils.Endpoints.USERS_TWEETS_ENDPOINT;
import static Twitter4J.Utils.TextFormatting.cleanText;
import static Twitter4J.Utils.TweetTextContainsCommasEscape.escapeCommas;

public class GetUsersTweets {

    /**
     * This method takes the Twitter ID of a user, gets as many tweets as the Twitter API allows
     * (roughly ~3200 using Consumer Licence), captures them and stores the data as a Tweet Java Object,
     * and stores each tweet in a List of Tweet objects, referred to as the tweetList,
     * the tweetList can be stored for future reference, or have new actions performed against it,
     * this method returns a tweetList of null if no tweets are found for userId,
     * or upto ~3200 Tweets if userId is valid.
     *
     * @author Ryan Llewellyn
     * @param userId The Twitter ID of the searched for user, can be retrieved using the GetUserId method.
     * @return tweetList A list of the searched for users Tweets.
     * @throws TwitterException An Exception from the Twitter API, e.g. invalid Keys or Over request limit.
     * @throws InterruptedException An exception which interrupts the execution of the program.
     */
    public static List<Tweet> getUserTweets(Long userId, Twitter twitter) throws TwitterException, InterruptedException {

        // Create Instance of List of Tweets
        List<Tweet> tweetList = new ArrayList<>();

        // Check whether userId entered is blank or not
        if (userId != null) {

            // Initialise starting page number
            int pageNo = 0;

            // Statuses from API, store in this list
            List<Status> statuses = new ArrayList<>();

            // infinite loop to get all tweets
            while (true) {

                // Check if we need to sleep,
                // in case we have exceeded the API limit
                TwitterCheckNeedToSleep.twitterCheckNeedToSleep(USERS_SEARCHFAMILY.getEndpoints(), USERS_TWEETS_ENDPOINT.getEndpoints(), twitter);

                // Try catch block to get users tweets from twitter api
                try {

                    // Define size of statuses
                    int size = statuses.size();

                    // Paging used as twitter API does not allow for more than 200 tweets to be gathered at a time
                    Paging page = new Paging(pageNo, 200);

                    // Add all retrieved tweets to statuses list
                    statuses.addAll(twitter.getUserTimeline(userId, page));

                    // Loop through all statuses from twitter user timeline
                    for (Status status : twitter.getUserTimeline(userId, page)) {

                        // Create new Tweet object containing values from API
                        Tweet tweet = new Tweet(
                                String.valueOf(status.getId()),
                                status.getCreatedAt().toString(),
                                status.getUser().getScreenName(),
                                status.getUser().getName(),
                                escapeCommas(cleanText(status.getText()))
                        );

                        // Add gotten Tweet to the Tweetlist
                        tweetList.add(tweet);

                    }

                    // increment pageno
                    pageNo++;

                    // if theres no new statuses to get, break out of the infinite loop
                    if (statuses.size() == size) { break; }

                    // Catch an exception from the Twitter API
                } catch(TwitterException e) { e.printStackTrace(); }
            }

        } // End of if statement

        // return tweetList, will be empty if no user found or full if found
        return tweetList;
    }
}