package use_case.add_friend;

import entity.User;
import entity.UserFactory;
import interface_adapter.addFriend.AddFriendPresenter;

/**
 * The Add Friend Interactor.
 */
public class AddFriendInteractor implements AddFriendInputBoundary {
    private final AddFriendUserDataAccessInterface userDataAccessObject;
    private final AddFriendOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public AddFriendInteractor(AddFriendUserDataAccessInterface userDataAccessObject,
                               AddFriendOutputBoundary userPresenter, UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(AddFriendInputData addFriendInputData) {
        final String username = addFriendInputData.getUsername();
        final String friendUserName = addFriendInputData.getFriendUserName();
        if (userDataAccessObject.isFriend(friendUserName, username)) {
            userPresenter.displayActiveFriendStatus();
        }
        // Add statements:
        // if user has sent outgoing request to friendUserName, not accepted
        // if user has recieved outgoing request to friendUserName, not accepted
        // if no requests exist between user and friendUserName
        if (userDataAccessObject.sentFriendRequest(friendUserName, username)) {
            userPresenter.displaySentFriendRequestStatus();
        }

        if (userDataAccessObject.recievedFriendRequest(friendUserName, username)) {
            userPresenter.displayRecievedFriendRequestStatus();
        }

    }

}

