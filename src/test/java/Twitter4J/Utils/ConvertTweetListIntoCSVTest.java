package Twitter4J.Utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConvertTweetListIntoCSVTest {

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
    void convertTweetListIntoCSV() {
        try {
            assertNotNull(tweetListTest);
            assertTrue(tweetListTest.size() > 0);
            boolean returnedBoolean = ConvertTweetListIntoCSV.convertTweetListIntoCSV(tweetListTest, "realdonaldtrump", "political", "UTF-8");
            assertTrue(returnedBoolean);
            System.out.println("Conversion of TweetList to CSV File: PASSED");
        } catch (Exception e) {
            System.out.println("Conversion of TweetList to CSV File: FAILED");
            fail("Exception: " + e);
        }
    }
}