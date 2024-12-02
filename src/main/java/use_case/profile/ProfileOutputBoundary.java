package use_case.profile;

/**
 * The Presenter for the Profile Use Case.
 */
public interface ProfileOutputBoundary {

    /**
     * Prepares the success view for the Profile Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ProfileOutputData outputData);

    /**
     * Prepares the fail view for the Profile Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Feed View.
     */
    void switchToFeedView();

    /**
     * Switches to the Friends View.
     */
    void switchToFriendsView();
}
