package Twitter4J.Utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class RegexToAWordInTweetTest {

    // Create arraylist of Tweets to hold our tweets
    private static final List<Tweet> tweetListTest = new ArrayList<>();

    // add new tweets to the arraylist
    @BeforeAll
    static void beforeAll() {
        tweetListTest.add(new
                Tweet("12345",
                "2021-05-03",
                "realdonaldtrump",
                "Donald Trump",
                TweetTextContainsCommasEscape.escapeCommas("This election was a HOAX!, I blame the FAKE NEWS!")
        ));
        tweetListTest.add(new
                Tweet("67890",
                "2021-05-03",
                "realdonaldtrump",
                "Donald Trump",
                TweetTextContainsCommasEscape.escapeCommas("Maybe injecting bleach doesn't cure COVID-19, I blame Fauci for not correcting me! VERY SAD!")
        ));
    }

    @Test
    void regexToAWordInTweet() {
        try {
            boolean wordFound = RegexToAWordInTweet.regexToAWordInTweet("injecting", tweetListTest.get(1).tweetText);
            assertTrue(wordFound);
            System.out.println("Get Word from File: PASSED");
        } catch (Exception e) {
            System.out.println("Get Word from File: FAILED");
            fail("Exception: " + e);
        }
    }
}