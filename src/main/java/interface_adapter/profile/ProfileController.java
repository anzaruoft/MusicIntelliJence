package interface_adapter.profile;

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
     */
    public void execute(String username) {
        final ProfileInputData profileInputData = new ProfileInputData(username);
        userProfileUseCaseInteractor.execute(profileInputData);
    }

    /**
     * Executes the "switch to LoggedInView" Use Case.
     */
    public void switchToLoggedInView() {
        userProfileUseCaseInteractor.switchToLoggedInView();
    }

}
