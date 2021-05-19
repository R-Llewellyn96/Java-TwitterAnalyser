package Twitter4J.Utils;

import twitter4j.RateLimitStatus;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterCheckNeedToSleep {

    /**
     * Check whether we need to stop sending requests to the Twitter API if our calls remaining is zero,
     * to avoid being locked out of the Twitter API we wait until our calls limit is reset.
     *
     * @author Ryan Llewellyn
     * @param searchFamily Start point of API endpoint, i.e. "users", "search" etc...
     * @param endPoint Specific Twitter API Endpoint URI, i.e. "/users/:id/tweets".
     * @param twitter The Twitter API used for access to the Twitter API
     * @throws TwitterException An Exception from the Twitter API, e.g. invalid Keys or Over request limit.
     * @throws InterruptedException An exception which interrupts the execution of the program.
     */
    public static void twitterCheckNeedToSleep(String searchFamily, String endPoint, Twitter twitter) throws TwitterException, InterruptedException {

        // Get the status of our API limits, to prevent blocking
        RateLimitStatus searchTweetsRateLimit = GetRateLimit.getRateLimit(searchFamily, endPoint, twitter);

        //	Check whether we have to wait for our rate limit to reset
        if (searchTweetsRateLimit.getRemaining() == 0) {

            // If we need to wait print to terminal for debugging
            System.out.printf("!!! Sleeping for %d seconds due to rate limits\n", searchTweetsRateLimit.getSecondsUntilReset());

            //	If you sleep exactly the number of seconds, you can make your query fail
            //	and get an error for exceeding twitters API rate limit.
            // 	Adding a delay avoids this, i'm aware this is bad practice
            Thread.sleep((searchTweetsRateLimit.getSecondsUntilReset() + 61) * 1000L);
        }
    }
}
