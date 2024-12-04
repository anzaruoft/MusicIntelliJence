package use_case.friendProfile;

import entity.User;

import data_access.InMemoryUserDataAccessObject;

/**
 * The Friend Profile interactor.
 */
public class FriendProfileInteractor implements FriendProfileInputBoundary {
    private final FriendProfileUserDataAccessInterface userDataAccessObject;
    private final FriendProfileOutputBoundary friendProfilePresenter;
    private InMemoryUserDataAccessObject inMemoryUserDataAccessObject;

    public FriendProfileInteractor(FriendProfileUserDataAccessInterface userDataAccessInterface,
                                   FriendProfileOutputBoundary friendProfileOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.friendProfilePresenter = friendProfileOutputBoundary;
    }

    @Override
    public void execute(FriendProfileInputData friendProfileInputData) {
        final String username = friendProfileInputData.getUsername();

        // Added
        inMemoryUserDataAccessObject = new InMemoryUserDataAccessObject();
        inMemoryUserDataAccessObject.setCurrentUsername(username);

        if (userDataAccessObject.existsByName(username)) {
            final User user = userDataAccessObject.get(username);
            final FriendProfileOutputData friendProfileOutputData = new FriendProfileOutputData(user.getName(),
                    user.getPosts(),
                    user.getTopSongs(),
                    user.getFriends());
            friendProfilePresenter.prepareSuccessView(friendProfileOutputData);
        }
    }

    @Override
    public void switchToFriendsView() {
        friendProfilePresenter.switchToFriendsView();
    }
}
