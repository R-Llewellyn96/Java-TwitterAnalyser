package Twitter4J.Utils;

import twitter4j.RateLimitStatus;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.Map;

public class GetTwitterAPIEndpoints {

    /**
     * Used to get API endpoints for different search families such as "users" or "tweets".
     *
     * @author Ryan Llewellyn
     * @param searchFamily The start of the Twitter API endpoint family you wish to search,
     *                    i.e. "users" or "search".
     * @param twitter The Twitter API instance created for access to Twitter Servers.
     */
    public static void getEndpointsForFamily(String searchFamily, Twitter twitter) {

        // Try to get twitter object and see if we have an endpoint for our search term
        try {

            Map<String, RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus(searchFamily);
            for (String endpoint : rateLimitStatus.keySet()) {
                System.out.println(endpoint);
            }
            // Catch the exception if search term of endpoint does not exist
        } catch (TwitterException e) {
            System.out.println("Unable to get endpoints for :" + searchFamily);
        }
    }

}
