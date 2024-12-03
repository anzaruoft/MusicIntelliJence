package interface_adapter.friends;

import use_case.friends.FriendsInputBoundary;
import use_case.friends.FriendsInputData;

/**
 * Controller for the Friends List Use Case.
 */
public class FriendsController {

    private final FriendsInputBoundary userFriendsUseCaseInteractor;

    public FriendsController(FriendsInputBoundary userFriendsUseCaseInteractor) {
        this.userFriendsUseCaseInteractor = userFriendsUseCaseInteractor;
    }

    /**
     * Executes the Friends Use Case.
     * @param username the username of the user whose friends are displayed.
     */
    public void execute(String username) {
        final FriendsInputData friendsInputData = new FriendsInputData(username);
        userFriendsUseCaseInteractor.execute(friendsInputData);
    }

    /**
     * Executes the "back to profile" Friends Use Case.
     */
    public void backToProfileView() {
        userFriendsUseCaseInteractor.backToProfileView();
    }

    /**
     * Executes the "switch to Friend Profile" Friends Use Case.
     * @param friendUsername the username of the friends profile to view.
     */
    public void switchToFriendProfile(String friendUsername) {
        userFriendsUseCaseInteractor.switchToFriendProfile(friendUsername);
    }
}
