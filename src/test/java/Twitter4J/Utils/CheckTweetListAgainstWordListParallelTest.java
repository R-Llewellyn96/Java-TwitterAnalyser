package Twitter4J.Utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckTweetListAgainstWordListParallelTest {

    // Create arraylist of Tweets to hold our tweets
    private static final List<TweetWithSentiment> tweetListTest = new ArrayList<>();

    // add new tweets to the arraylist
    @BeforeAll
    static void beforeAll() {
        tweetListTest.add(new
                TweetWithSentiment("12345",
                "2021-05-03",
                "realdonaldtrump",
                "Donald Trump",
                TweetTextContainsCommasEscape.escapeCommas("This election was a HOAX!, I blame the FAKE NEWS!"),
                "2"
        ));
        tweetListTest.add(new
                TweetWithSentiment("67890",
                "2021-05-03",
                "realdonaldtrump",
                "Donald Trump",
                TweetTextContainsCommasEscape.escapeCommas("Maybe injecting bleach doesn't cure COVID-19, I blame Fauci for not correcting me! VERY SAD!"),
                "2"
        ));
    }

    @Test
    void checkTweetListAgainstWordlistParallel() {
        try {
            assertNotNull(tweetListTest);
            assertTrue(tweetListTest.size() > 0);
            String[] arrayOfStrings = {"election", "covid", "fake news"};
            List<TweetWithSentiment> returnedTweets = CheckTweetListAgainstWordListParallel.checkTweetListAgainstWordlistParallel(tweetListTest, arrayOfStrings);
            assertFalse(returnedTweets.isEmpty());

            // Print out the text of each returned tweet
            for (TweetWithSentiment tweet : returnedTweets) {
                System.out.println(tweet.tweetText);
            }
            System.out.println("Check TweetList against WordList parallelized: PASSED");
        } catch (Exception e) {
            System.out.println("Check TweetList against WordList parallelized: FAILED");
            fail("Exception: " + e);
        }
    }
}