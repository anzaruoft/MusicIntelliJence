package use_case.other_profile;

/**
 * Input Boundary for actions which are related to a user profile.
 */
public interface OtherProfileInputBoundary {

    /**
     * Executes the profile.
     * @param otherProfileInputData the input data
     */
    void execute(OtherProfileInputData otherProfileInputData);

    /**
     * Executes the switch to feed view use case.
     */
    void switchToFeedView();
}