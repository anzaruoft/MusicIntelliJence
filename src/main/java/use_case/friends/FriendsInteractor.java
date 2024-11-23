package use_case.friends;

import entity.User;
import use_case.friendProfile.FriendProfileOutputData;

public class FriendsInteractor implements FriendsInputBoundary {
    private final FriendsUserDataAccessInterface userDataAccessObject;
    private final FriendsOutputBoundary friendsPresenter;

    public FriendsInteractor(FriendsUserDataAccessInterface userDataAccessObject,
                             FriendsOutputBoundary friendsPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.friendsPresenter = friendsPresenter;
    }

    @Override
    public void execute(FriendsInputData friendsInputData) {
        final String username = friendsInputData.getUsername();

        if (userDataAccessObject.existsByName(username)) {
            final User user = userDataAccessObject.get(username);
            final FriendsOutputData friendsOutputData = new FriendsOutputData(user.getFriends());
            friendsPresenter.prepareSuccessView(friendsOutputData);
        }
    }

    @Override
    public void backToProfileView() {
        friendsPresenter.backToProfileView();
    }

}
