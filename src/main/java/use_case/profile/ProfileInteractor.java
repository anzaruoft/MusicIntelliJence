package use_case.profile;

import entity.User;

/**
 * The Profile interactor.
 */
public class ProfileInteractor implements ProfileInputBoundary {
    private final ProfileUserDataAccessInterface userDataAccessObject;
    private final ProfileOutputBoundary profilePresenter;

    public ProfileInteractor(ProfileUserDataAccessInterface userDataAccessInterface,
                             ProfileOutputBoundary profileOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.profilePresenter = profileOutputBoundary;
    }

    @Override
    public void execute(ProfileInputData profileInputData) {
        final String username = profileInputData.getName();
        final User user = userDataAccessObject.get(username);
        final ProfileOutputData profileOutputData = new ProfileOutputData(
                user.getName(),
                user.getFriends(),
                user.getPosts(),
                user.getTopSongs(),
                user);
        userDataAccessObject.updateUserPosts(user);
        profilePresenter.prepareSuccessView(profileOutputData);
    }

    @Override
    public void switchToFeedView() {
        profilePresenter.switchToFeedView();
    }

    @Override
    public void switchToFriendsView() {
        profilePresenter.switchToFriendsView();
    }
}
