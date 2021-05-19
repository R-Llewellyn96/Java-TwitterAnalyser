package Twitter4J.Utils;

import twitter4j.RateLimitStatus;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.Map;

public class GetRateLimit {

    /**
     * Must stay within the API calls limit, specified in the Twitter API or Twitter will revoke access,
     * to ensure that we stay within the limits, we ask Twitter API how many remaining calls we can make.
     *
     * searchFamily is typically "users" for users, or "search" for keyword search,
     * endPoint is typically "/users/by/username/:username" for users, or "/search/tweets" for tweets.
     *
     * @author Ryan Llewellyn
     * @param searchFamily Start point of API endpoint, i.e. "users", "search" etc...
     * @param endPoint Specific Twitter API Endpoint URI, i.e. "/users/:id/tweets".
     * @return RateLimitStatus The number of remaining requests available for a given Twitter API Endpoint.
     * @throws TwitterException An Exception from the Twitter API, e.g. invalid Keys or Over request limit.
     */
   public static RateLimitStatus getRateLimit(String searchFamily, String endPoint, Twitter twitter) throws TwitterException {

        //	This returns all the various rate limits in effect for us with the Twitter API
       Map<String, RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus(searchFamily);

        // This finds the rate limit specifically for doing the search API call we use in this program,
       // and returns the search limit to method caller
       return rateLimitStatus.get(endPoint);
    }
}
