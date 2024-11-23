package interface_adapter.friendProfile;

import use_case.friendProfile.FriendProfileInputBoundary;
import use_case.friendProfile.FriendProfileInputData;

/**
 * The controller for the Friend Profile Use Case.
 */

public class FriendProfileController {

    private final FriendProfileInputBoundary friendProfileUseCaseInteractor;

    public FriendProfileController(FriendProfileInputBoundary friendProfileUseCaseInteractor) {
        this.friendProfileUseCaseInteractor = friendProfileUseCaseInteractor;
    }

    /**
     * Executes the Login Use Case.
     * @param username the username of the user logging in.
     */

    public void execute(String username) {
        final FriendProfileInputData friendProfileInputData = new FriendProfileInputData(username);

        friendProfileUseCaseInteractor.execute(friendProfileInputData);
    }

    /**
     * Executes the "switch to FriendsView" Use Case.
     */
    public void switchToFriendsView() {
        friendProfileUseCaseInteractor.switchToFriendsView();
    }
}
