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
        final FeedInputData feedInputData = new FeedInputData(username);
        feedInteractor.execute(feedInputData);
    }

    /**
     * This is the switchToSongSearchView function.
     * @param username the username.
     */
    public void switchToSongSearchView(String username) {
        feedInteractor.switchToSongSearchView(username);
    }

    /**
     * This is the switchToFeedView function.
     * @param username the username of the user.
     */
    public void switchtoProfileView(String username) {

        feedInteractor.switchToProfileView(username);
    }

    /**
     * This is the switchToProfileSearchView function.
     * @param username the username of the user.
     */
    public void switchToProfileSearchView(String username) {
        feedInteractor.switchToProfileSearchView(username);
    }

    /**
     * This is the switchToChangePasswordView function.
     */
    public void switchToChangePasswordView() {
        feedInteractor.switchToChangePasswordView();
    }
}
