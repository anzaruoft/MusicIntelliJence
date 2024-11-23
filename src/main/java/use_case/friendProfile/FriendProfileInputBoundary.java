package use_case.friendProfile;

/**
 * The output boundary for the Friend Profile Use Case.
 */
public interface FriendProfileInputBoundary {

    /**
     * Execute the Friend Profile Use Case.
     * @param friendProfileInputData the input data for this use case
     */
    void execute(FriendProfileInputData friendProfileInputData);

    /**
     * Execute the back to Friends View Use Case.
     */
    void switchToFriendsView();
}
