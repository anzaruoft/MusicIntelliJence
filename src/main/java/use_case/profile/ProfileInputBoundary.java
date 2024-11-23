package use_case.profile;

/**
 * Input Boundary for actions which are related to a user profile.
 */
public interface ProfileInputBoundary {

    /**
     * Executes the profile.
     * @param profileInputData the input data
     */
    void execute(ProfileInputData profileInputData);

    /**
     * Executes the switch to feed view use case.
     */
    void switchToFeedView();
}
