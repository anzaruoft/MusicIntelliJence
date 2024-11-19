package interface_adapter.feed;

/**
 * The State information representing the feed user.
 */
public class FeedState {
    private String username = "";

    public FeedState(String username) {
        this.username = username;
    }

    public FeedState() {

    }

    public String getUsername() {
        return username;
    }
}
