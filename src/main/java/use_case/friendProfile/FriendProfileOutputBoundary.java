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
     * Prepares the failure view for the Friend Profile Use Case.
     * @param errorMessage the explanation of the failure
     */

    void prepareFailView(String errorMessage);

    /**
     * Switches to the Friend Profile View.
     */
    void switchToFriendsView();

}
