package use_case.friendProfile;

import entity.User;

/**
 * The Change Password interactor.
 */
public class FriendProfileInteractor implements FriendProfileInputBoundary {
    private final FriendProfileUserDataAccessInterface userDataAccessObject;
    private final FriendProfileOutputBoundary friendProfilePresenter;

    public FriendProfileInteractor(FriendProfileUserDataAccessInterface userDataAccessInterface,
                           FriendProfileOutputBoundary friendProfileOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.friendProfilePresenter = friendProfileOutputBoundary;
    }

    @Override
    public void execute(FriendProfileInputData friendProfileInputData) {
        final String username = friendProfileInputData.getUsername();

        if (!userDataAccessObject.existsByName(username)) {
            friendProfilePresenter.prepareFailView(username);
        }
        else {
            final User user = userDataAccessObject.get(username);
            final FriendProfileOutputData friendProfileOutputData = new FriendProfileOutputData(user.getName(),
                    user.getPosts(),
                    user.getTopSongs());
            friendProfilePresenter.prepareSuccessView(friendProfileOutputData);
        }
    }
}
