package Twitter4J.Utils;

/**
 * Create Tweet object, containing values from a tweet, from the Twitter API.
 *
 * @author Ryan Llewellyn
 */
public class Tweet {

    public String id;
    public String createdAt;
    public String username;
    public String displayName;
    public String tweetText;

    public Tweet(String id, String createdAt, String username, String displayName, String tweetText) {
        this.id = id;
        this.createdAt = createdAt;
        this.username = username;
        this.displayName = displayName;
        this.tweetText = tweetText;
    }

    public String getId() {
        return id;
    }
}
