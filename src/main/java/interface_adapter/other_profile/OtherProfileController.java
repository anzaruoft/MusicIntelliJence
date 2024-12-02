package interface_adapter.other_profile;

import use_case.other_profile.OtherProfileInputBoundary;
import use_case.other_profile.OtherProfileInputData;

/**
 * Controller for the Other Profile Use Case.
 */
public class OtherProfileController {

    private final OtherProfileInputBoundary otherUserProfileUseCaseInteractor;

    public OtherProfileController(OtherProfileInputBoundary otherProfileUseCaseInteractor) {
        this.otherUserProfileUseCaseInteractor = otherProfileUseCaseInteractor;
    }

    /**
     * Executes the Other Profile Use Case.
     * @param otherUsername the username of the other profile to view.
     */
    public void execute(String otherUsername) {
        final OtherProfileInputData otherProfileInputData = new OtherProfileInputData(otherUsername);
        otherUserProfileUseCaseInteractor.execute(otherProfileInputData);
    }

    /**
     * Executes the "switch to LoggedInView" Use Case.
     */
    public void switchToFeedView() {
        otherUserProfileUseCaseInteractor.switchToFeedView();
    }
}
