package Twitter4J.Utils;

import twitter4j.TwitterFactory;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

public class GetTokenFromTwitterAPI {

    /**
     * Retrieve the "bearer" token from Twitter in order to make application-authenticated calls.
     *
     * This is the first step in doing application authentication, as described in Twitter's documentation at
     * https://dev.twitter.com/docs/auth/application-only-auth
     *
     * Note that if there's an error in this process, we just print a message and quit.  That's a pretty
     * dramatic side effect, and a better implementation would pass an error back up the line...
     *
     * @param consumerKey Consumer Key from Twitter Developer Account for API access.
     * @param consumerSecret Consumer Secret from Twitter Developer Account for API access.
     * @return	The oAuth2 bearer token.
     */
    public static OAuth2Token getOAuth2Token(String consumerKey, String consumerSecret)
    {
        OAuth2Token token = null;
        ConfigurationBuilder cb;

        cb = new ConfigurationBuilder();
        cb.setApplicationOnlyAuthEnabled(true);

        cb.setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret);

        try {

            token = new TwitterFactory(cb.build()).getInstance().getOAuth2Token();

        } catch (Exception e) {

            System.out.println("Could not get OAuth2 token");
            e.printStackTrace();
            System.exit(0);

        }

        return token;
    }
}
