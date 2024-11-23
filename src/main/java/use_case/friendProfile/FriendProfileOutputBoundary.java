package use_case.friendProfile;

/**
 * The output boundary for the Friend Profile Use Case.
 */
public interface FriendProfileOutputBoundary {

    /**
     * Prepares the success view for the Friend Profile Use Case.
     * @param outputData the output data
     */

    void prepareSuccessView(FriendProfileOutputData outputData);

    /**
     * Switches to the Friend Profile View.
     */
    void switchToFriendsView();

}
