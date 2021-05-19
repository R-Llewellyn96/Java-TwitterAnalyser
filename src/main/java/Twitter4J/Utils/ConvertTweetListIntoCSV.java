package Twitter4J.Utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class ConvertTweetListIntoCSV {

    // CSV separator defined here, if your country uses a different value, specify it here
    private static final String CSV_SEPARATOR = ",";

    /**
     * This method takes a List of Tweets to be written to a CSV file and attempts to write them to a CSV file,
     * such a CSV file uses the format "@username_tweetType_tweets.csv" where username is the twitter users username,
     * tweetType refers to the content of tweets such as abusive language, political speech or racial abuse etc..,
     * the final parameter passed to this method is charset, which defines the character set encoding,
     * used for the CSV file, I recommend UTF-8 for Anglophone Nations, but if your requirements differ,
     * use the appropriate character set.
     *
     * @author Ryan Llewellyn
     * @param tweetList The List of Tweets to be written to a CSV file.
     * @param username The Twitter username of the Tweet author.
     * @param tweetType The Classification of Tweet content.
     * @param charset The Character Set used for encoding the CSV file.
     * @return Boolean true or false, dependant on whether writing to file was successful or not,
     * true for success, false for failure.
     */
    public static boolean convertTweetListIntoCSV(List<TweetWithSentiment> tweetList, String username, String tweetType, String charset) {

        // Check passed list isn't empty, if it is just return false to indicate failure
        if (tweetList.size() > 0 && !username.isBlank() && !tweetType.isBlank() && !charset.isBlank()) {

            String csvFileName = ("@" + username + "_" + tweetType + "_tweets.csv");

            // Try to write contents to a CSV file
            try {

                // Create file writer, specify filename and encoding
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(csvFileName), charset));

                // Write first line, containing headers
                String[] headers = {"id", "createdAt", "username", "displayName", "tweetText", "sentimentScore"};
                String header = headers[0] + CSV_SEPARATOR +
                        headers[1] + CSV_SEPARATOR +
                        headers[2] + CSV_SEPARATOR +
                        headers[3] + CSV_SEPARATOR +
                        headers[4] + CSV_SEPARATOR +
                        headers[5];
                writer.write(header);
                writer.newLine();

                // Loop through each tweet in the tweetList and write to file
                for (TweetWithSentiment tweet : tweetList) {
                    String line = tweet.id +
                            CSV_SEPARATOR +
                            tweet.createdAt +
                            CSV_SEPARATOR +
                            tweet.username +
                            CSV_SEPARATOR +
                            tweet.displayName +
                            CSV_SEPARATOR +
                            tweet.tweetText +
                            CSV_SEPARATOR +
                            tweet.sentimentScore;
                    writer.write(line);
                    writer.newLine();
                }

                // Empty the contents of the buffer and close the object in memory, we don't need it anymore
                writer.flush();
                writer.close();

                // Return true to indicate writing was a success!
                return true;

                // If writing fails, catch the exception and return false to indicate failure,
                // error messages included for IDE debugging and testing only, not GUI use.
            } catch (Exception e) {
                System.out.println("Exception: " + e);
                System.out.println("Acutal Cause: " + e.getCause());
                return false;
            }

            // If the arguments passed to the program are blank, return false to caller and do nothing
        } else {
            return false;
        }
    }
}
