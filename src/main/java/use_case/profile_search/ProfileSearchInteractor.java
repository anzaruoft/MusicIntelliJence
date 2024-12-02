package use_case.profile_search;

import entity.User;

/**
 * The Interactor for the Profile Search Use Case.
 */
public class ProfileSearchInteractor implements ProfileSearchInputBoundary {

    private final ProfileSearchUserDataAccessInterface userDataAccessObject;
    private final ProfileSearchOutputBoundary profileSearchPresenter;

    public ProfileSearchInteractor(ProfileSearchUserDataAccessInterface userDataAccessObject,
                                   ProfileSearchOutputBoundary profileSearchPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.profileSearchPresenter = profileSearchPresenter;
    }

    @Override
    public void execute(ProfileSearchInputData profileSearchInputData) {
        final String inputUsername = profileSearchInputData.getUsername();

        if (userDataAccessObject.existsByName(inputUsername)) {
            final User inputUser = userDataAccessObject.get(inputUsername);
            final int inputUserFriendCount = inputUser.getFriends().length();

            final ProfileSearchOutputData profileSearchOutputData = new ProfileSearchOutputData(inputUserFriendCount,
                    inputUsername);

            profileSearchPresenter.prepareSuccessView(profileSearchOutputData);

        }
        else {
            profileSearchPresenter.prepareFailView("User\"" + inputUsername + "\" does not exist");
        }
    }

    @Override
    public void switchToFeedView() {
        profileSearchPresenter.switchToFeedView();
    }
}

// DATA ACCess Interface code NOT written!!!!
