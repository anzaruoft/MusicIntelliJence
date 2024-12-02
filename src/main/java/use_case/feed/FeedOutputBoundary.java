package use_case.feed;

/**
 * This is FeedOutputBoundary.
 */
public interface FeedOutputBoundary {
    /**
     * Prepares the success view for the Feed Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(FeedOutputData outputData);

    /**
     * Prepares the failure view for the Feed Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * This is swtichToProfileView.
     * @param username type String.
     */
    void switchToProfileView(String username);

    /**
     * This is switchToSongSearchView.
     * @param username type String.
     */
    void switchToSongSearchView(String username);

    /**
     * This is switchToChangePasswordView.
     */
    void switchToChangePasswordView();

    /**
     * This is switchToProfileSearchView.
     * @param username is type String.
     */
    void switchToProfileSearchView(String username);
}
