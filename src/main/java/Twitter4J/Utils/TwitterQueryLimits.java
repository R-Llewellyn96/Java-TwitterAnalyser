package Twitter4J.Utils;

/**
 *  This enum contains the number of tweets to request in each Twitter API call,
 *  100 is the maximum allowed per query in the API, we also have a limit of 450 queries,
 *  so we can set the number of queries we wish to make, so the number we can retrieve is
 *  TWEETS_PER_QUERY * MAX_QUERIES.
 *
 * @author Ryan Llewellyn
 */
public enum TwitterQueryLimits {

    TWEETS_PER_QUERY(100),
    MAX_QUERIES(5);

    // Declare value
    private final int queryLimits;
    TwitterQueryLimits(int queryLimits) { this.queryLimits = queryLimits; }
    public int getQueryLimits() { return queryLimits; }
}
