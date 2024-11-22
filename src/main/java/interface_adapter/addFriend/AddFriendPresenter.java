package interface_adapter.addFriend;

import use_case.add_friend.AddFriendOutputBoundary;

/**
 * The Presenter for the Add Friend Use Case.
 */
public class AddFriendPresenter implements AddFriendOutputBoundary {

    @Override
    public void displayActiveFriendStatus() {
        // To DO: implement to display an active friend status on the view model we choose to show it on.
    }


    @Override
    public void displaySentFriendRequestStatus() {
        // To DO: implement to display an outgoing friend request on the view model, from User to Potential friend.
    }

    @Override
    public void displayRecievedFriendRequestStatus() {
        // To DO: implement to display an incoming friend request on the view model, from Potential friend to User.
    }
}
