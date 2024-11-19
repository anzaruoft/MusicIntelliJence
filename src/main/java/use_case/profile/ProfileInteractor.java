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
        System.out.println("THIS RANNNNNNNN");
        final String username = profileInputData.getUsername();
        final User user = userDataAccessObject.get(username);
        final ProfileOutputData profileOutputData = new ProfileOutputData(user.getName(),
                user.getFriends(),
                user.getPosts(),
                user.getTopSongs());
        System.out.println("DID NOT ERROR OUT");
        System.out.println(profileOutputData);
        profilePresenter.prepareSuccessView(profileOutputData);
    }

    @Override
    public void switchToFeedView() {
        profilePresenter.switchToFeedView();
    }
}
