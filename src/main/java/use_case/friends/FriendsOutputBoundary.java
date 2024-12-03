package use_case.friends;

/**
 * The output boundary for the Friends Profile Use Case.
 */
public interface FriendsOutputBoundary {

    /**
     * prepares the success view for the Friends Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(FriendsOutputData outputData);

    /**
     * prepares the "back to profile" view Use Case.
     */
    void backToProfileView();

    /**
     * prepares the "switch to friend profile" view Use Case.
     */
    void switchToFriendView(String friendUsername);
}
