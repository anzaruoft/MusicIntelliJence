package use_case.add_friend;

/**
 * The output boundary for the Add Friend Use Case.
 */
public interface AddFriendOutputBoundary {
    /**
     * Prepares the Active Friend Status view for the Add Friend Use Case.
     * @param
     */

    void displayActiveFriendStatus();

    /**
     * Prepares the Sent Friend Status view for the Add Friend Use Case.
     * @param
     */

    void displaySentFriendRequestStatus();

    /**
     * Prepares the Received Friend Status view for the Add Friend Use Case.
     * @param
     */

    void displayRecievedFriendRequestStatus();

}

// TO DO: Implement Parameters