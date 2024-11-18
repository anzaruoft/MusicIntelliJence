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
     * @param username the username data
     */
    void prepareFailView(String username);

    /**
     * Switches to the Logged In View.
     */
    void switchToLoggedInView();
}
