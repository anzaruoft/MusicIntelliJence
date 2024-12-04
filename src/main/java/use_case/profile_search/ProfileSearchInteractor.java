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
        final String inputUsername = profileSearchInputData.getSearchedUsername();
        final String thisUsername = profileSearchInputData.getThisUsername();

        if (!userDataAccessObject.existsByName(inputUsername)) {
            profileSearchPresenter.prepareFailView(inputUsername + ": Account does not exist.");
        }
        else {
            if (inputUsername.equals(thisUsername)) {
                profileSearchPresenter.prepareFailView(thisUsername + ": is your account.");
            }
            else {
                final User thisUser = userDataAccessObject.get(thisUsername);
                final User inputUser = userDataAccessObject.get(inputUsername);

                final ProfileSearchOutputData profileSearchOutputData =
                        new ProfileSearchOutputData(
                                inputUser.getFriends().length(),
                                inputUser.getName(),
                                thisUser.getName()
                        );
                profileSearchPresenter.prepareSuccessView(profileSearchOutputData);

            }
        }
    }

    @Override
    public void switchToFeedView() {
        profileSearchPresenter.switchToFeedView();
    }

}
