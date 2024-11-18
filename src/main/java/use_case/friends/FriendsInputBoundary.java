package use_case.friends;

/**
 * Input Boundary for actions which are related to viewing friends list.
 */
public interface FriendsInputBoundary {

    /**
     * Executes the friends use case.
     * @param friendsInputData the input data.
     */
    void execute(FriendsInputData friendsInputData);

    /**
     * Executes the switch to friends view use case.
     */
    void switchToFriendsView();
}
