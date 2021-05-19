package Twitter4J.Utils;

/**
 * This enum contains useful endpoints of the Twitter API,
 * namely the users and search endpoints.
 *
 * @author Ryan Llewellyn
 */
public enum Endpoints {
    USERS_SEARCHFAMILY("users"),
    USERS_TWEETS_ENDPOINT("/users/:id/tweets"),
    USERS_ID_ENDPOINT("/users/by/username/:username"),
    KEYWORD_SEARCHFAMILY("search"),
    KEYWORD_ENDPOINT("/search/tweets");

    private final String endpoints;

    Endpoints(String endpoints) { this.endpoints = endpoints; }

    public String getEndpoints() { return endpoints; }
}
