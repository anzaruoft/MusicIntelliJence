package interface_adapter.feed;

import use_case.feed.FeedInputBoundary;
import use_case.feed.FeedInputData;

/**
 * The controller for the Feed Use Case.
 */
public class FeedController {
    private final FeedInputBoundary feedInteractor;

    public FeedController(FeedInputBoundary feedInteractor) {
        this.feedInteractor = feedInteractor;
    }

    /**
     * Executes the Feed Use Case.
     * @param username the username of the user logged into the feed.
     *
     */
    public void execute(String username) {
        // remove later
        System.out.println("Feed Controller " + username);
        final FeedInputData feedInputData = new FeedInputData(username);
        feedInteractor.execute(feedInputData);
    }
}
