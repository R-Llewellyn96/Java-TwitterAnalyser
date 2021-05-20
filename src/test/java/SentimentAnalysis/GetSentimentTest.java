package SentimentAnalysis;

import Twitter4J.Utils.Tweet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static Twitter4J.Utils.TweetTextContainsCommasEscape.escapeCommas;
import static org.junit.jupiter.api.Assertions.*;

class GetSentimentTest {

    // Create ArrayList of Tweets to hold our tweets
    private static final List<Tweet> tweetListTest = new ArrayList<>();

    // add new tweets to the ArrayList
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
    void getSentimentScore() {
        try {
            assertNotNull(tweetListTest);
            assertTrue(tweetListTest.size() > 0);
            int returnedObj = GetSentiment.getSentimentScore(tweetListTest.get(2).tweetText);
            System.out.println(returnedObj);
            assertTrue(returnedObj > 0 && returnedObj < 5);
            System.out.println("Sentiment Analysis: PASSED");
        } catch (Exception e) {
            System.out.println("Sentiment Analysis: FAILED");
            fail("Exception: " + e);
        }
    }
}