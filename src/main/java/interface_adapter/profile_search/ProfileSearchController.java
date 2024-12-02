package interface_adapter.profile_search;

import use_case.profile_search.ProfileSearchInputBoundary;
import use_case.profile_search.ProfileSearchInputData;

/**
 * Controller for the Profile Search Use Case.
 */
public class ProfileSearchController {

    private final ProfileSearchInputBoundary profileSearchInteractor;

    public ProfileSearchController(ProfileSearchInputBoundary profileSearchUseCaseInteractor) {
        this.profileSearchInteractor = profileSearchUseCaseInteractor;
    }

    /**
     * Executes the Profile Search Use Case.
     * @param inputUsername username being searched
     * @param thisUsername the username of user searching
     */
    public void execute(String inputUsername, String thisUsername) {
        final ProfileSearchInputData profileSearchInputData = new ProfileSearchInputData(
                inputUsername, thisUsername
        );

        profileSearchInteractor.execute(profileSearchInputData);
    }

    /**
     * Returns to the Feed View.
     */
    public void switchToFeedView() {
        profileSearchInteractor.switchToFeedView();
    }

    /**
     * Goes to the Other Profile View (add more detailed comment later).
     */
    public void switchToOtherProfileView() {
    }

}
