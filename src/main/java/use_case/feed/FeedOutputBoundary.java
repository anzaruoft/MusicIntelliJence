package use_case.feed;

/**
 * The Feed Output Boundary.
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
     * Prepares to switch to profile View.
     * @param username the username parameter.
     */
    void switchToProfileView(String username);
    /**
     * Prepares to switch to Song SEar View.
     * @param username the username parameter.
     */
    void switchToSongSearchView(String username);

    void switchToChangePasswordView();
}
