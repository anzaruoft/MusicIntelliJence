package use_case.profile_search;

import use_case.friends.FriendsUserDataAccessInterface;
import use_case.profile_search.ProfileSearchInputData;
import use_case.profile_search.ProfileSearchInputBoundary;
import use_case.profile_search.ProfileSearchUserDataAccessInterface;
import java.util.List;
import entity.User;

/**
 * The Interactor for the Profile Search Use Case.
 */
public class ProfileSearchInteractor implements ProfileSearchInputBoundary {

    private final ProfileSearchUserDataAccessInterface userDataAccessObject;
    private final ProfileSearchOutputBoundary profileSearchPresenter;

    public ProfileSearchInteractor(ProfileSearchUserDataAccessInterface userDataAccessobject,
                                   ProfileSearchOutputBoundary profileSearchPresenter) {
        this.userDataAccessObject = userDataAccessobject;
        this.profileSearchPresenter = profileSearchPresenter;
    }

    @Override
    public void execute(ProfileSearchInputData profileSearchInputData) {
        final String inputUsername = profileSearchInputData.getUsername();

        if (userDataAccessObject.existsByName(inputUsername)) {
            final User inputUser = userDataAccessObject.getUser(inputUsername);
            final int inputUserFriendCount = (inputUser.getFriends()).length();

            final ProfileSearchOutputData profileSearchOutputData = new ProfileSearchOutputData(inputUserFriendCount,
                    inputUsername);

            profileSearchPresenter.prepareSuccessView(profileSearchOutputData);

        }
    }

    @Override
    public void switchToFeedView() {
        profileSearchPresenter.switchToFeedView();
    }
}

// DATA ACCess Interface code NOT written!!!!
