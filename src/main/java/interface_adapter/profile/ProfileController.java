package interface_adapter.profile;

import entity.User;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInputData;

/**
 * Controller for the Profile Use Case.
 */
public class ProfileController {

    private final ProfileInputBoundary userProfileUseCaseInteractor;

    public ProfileController(ProfileInputBoundary profileUseCaseInteractor) {
        this.userProfileUseCaseInteractor = profileUseCaseInteractor;
    }

    /**
     * Executes the Profile Use Case.
     * @param username the username of the profile to view.
     * @param user is type User.
     */
    public void execute(String username, User user) {
        final ProfileInputData profileInputData = new ProfileInputData(username, user);
        userProfileUseCaseInteractor.execute(profileInputData);
    }

    /**
     * Executes the "switch to FeedView" Use Case.
     */
    public void switchToFeedView() {
        userProfileUseCaseInteractor.switchToFeedView();
    }

    /**
     * Executes the "switch to FriendsView" Use Case.
     */
    public void switchToFriendsView() {
        userProfileUseCaseInteractor.switchToFriendsView();
    }
}
