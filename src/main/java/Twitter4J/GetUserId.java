package Twitter4J;

import Twitter4J.Utils.TwitterCheckNeedToSleep;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import static Twitter4J.Utils.Endpoints.USERS_ID_ENDPOINT;
import static Twitter4J.Utils.Endpoints.USERS_SEARCHFAMILY;

public class GetUserId {

    /**
     * This method takes a search parameter, the username to search, checks whether username has been entered,
     * checks whether we have remaining calls in the Twitter API, if so it sends a request to the Twitter API,
     * to get the corresponding userId of the username searched for, the method then returns the userId to its caller,
     * the userId will either be a Long containing the userId or be null if no user is found.
     *
     * @author Ryan Llewellyn
     * @param usernameToSearch The Twitter users Username you wish to search, i.e. @CNN.
     * @param twitter The Twitter API instance used for access to Twitter servers.
     * @return userId The Twitter ID of the user, who's username was searched for, used for requesting Tweets.
     */
    public Long getUserId(String usernameToSearch, Twitter twitter) {
        // search term twitter api endpoint: /users/by/username/:username

        // Create Instance of userId
        Long userId = null;

        // Check whether username entered is blank or not
        if (!usernameToSearch.isEmpty() || !usernameToSearch.isBlank()) {

            // Try catch block in case the Twitter API is unreachable or offline
            try {

                // Check if we need to sleep,
                // in case we have exceeded the API limit
                TwitterCheckNeedToSleep.twitterCheckNeedToSleep(USERS_SEARCHFAMILY.getEndpoints(), USERS_ID_ENDPOINT.getEndpoints(), twitter);

                try {

                    // Use Twitter API to show user info from @Username
                    User user = twitter.showUser(usernameToSearch);

                    // Assign userId to Long value
                    userId = user.getId();

                    // Print for debugging only, delete later
                    System.out.println("UserID for user: @" + usernameToSearch + " is ID: " + userId);
                } catch (TwitterException e) {
                    // do nothing, as our main method will return no username found
                }
            }

            // Catch an exception from the Twitter API
            catch(InterruptedException | TwitterException e) {
                e.printStackTrace();
            }
        } // End of if statement

        // Return userId to method caller,
        // will be null if no user found, or long if found
        return userId;
    }
}
