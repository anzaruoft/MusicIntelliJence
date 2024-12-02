package use_case.feed;

/**
 * The input data for the Feed Use Case.
 */
public class FeedInputData {

    private final String username;

    public FeedInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
