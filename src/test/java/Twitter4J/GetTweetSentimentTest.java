package Twitter4J;

import Twitter4J.Utils.Tweet;
import Twitter4J.Utils.TweetWithSentiment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static Twitter4J.Utils.TweetTextContainsCommasEscape.escapeCommas;
import static org.junit.jupiter.api.Assertions.*;

class GetTweetSentimentTest {

    // Create Linked List of Tweets to hold our tweets
    private static final List<Tweet> tweetListTest = new LinkedList<>();

    // add new tweets to the arraylist
    @BeforeAll
    static void beforeAll() {
        tweetListTest.add(new
                Tweet("12345",
                "2021-05-03",
                "realdonaldtrump",
                "Donald Trump",
                escapeCommas("This election was a HOAX!, I blame the FAKE NEWS!")
        ));
        tweetListTest.add(new
                Tweet("67890",
                "2021-05-03",
                "realdonaldtrump",
                "Donald Trump",
                escapeCommas("Maybe injecting bleach doesn't cure COVID-19, I blame doctors for not correcting me! VERY SAD!")
        ));
        tweetListTest.add(new
                Tweet("77777",
                "2021-05-04",
                "realdonaldtrump",
                "Donald Trump",
                escapeCommas("The mexicans arent sending their best, they send their worst, they bring crime, they bring drugs, they're rapists, some i assume are good people.")
        ));
    }

    @Test
    void getTweetSentiment() {
        try {
            assertNotNull(tweetListTest);
            assertTrue(tweetListTest.size() > 0);
            List<TweetWithSentiment> returnedObj = GetTweetSentimentParallel.getTweetSentiment(tweetListTest);
            assertFalse(returnedObj.isEmpty());
            for (TweetWithSentiment tweet : returnedObj) {
                System.out.println(tweet.sentimentScore);
            }
            System.out.println("Sentiment Analysis: PASSED");
        } catch (Exception e) {
            System.out.println("Sentiment Analysis: FAILED");
            fail("Exception: " + e);
        }
    }
}