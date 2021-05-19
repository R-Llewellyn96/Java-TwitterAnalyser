package Twitter4J.Utils;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

public class GetTwitterObject {

    /**
     * Get a fully application-authenticated Twitter object useful for making subsequent calls.
     *
     * @return	Twitter4J Twitter object that's ready for API calls.
     */
    public static Twitter getTwitter(String consumerKey, String consumerSecret) {

        // Define OAuth2 Token
        OAuth2Token token;

        //	First step, get a "bearer" token that can be used for our requests
        token = GetTokenFromTwitterAPI.getOAuth2Token(consumerKey, consumerSecret);

        //	Now, configure our new Twitter object to use application authentication and provide it with
        //	our CONSUMER key and secret and the bearer token we got back from Twitter
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setApplicationOnlyAuthEnabled(true);
        cb.setOAuthConsumerKey(consumerKey);
        cb.setOAuthConsumerSecret(consumerSecret);
        cb.setOAuth2TokenType(token.getTokenType());
        cb.setOAuth2AccessToken(token.getAccessToken());

        cb.setTweetModeExtended(true); // Required to get full 140 character tweets

        //	And create the Twitter object!
        return new TwitterFactory(cb.build()).getInstance();
    }
}
