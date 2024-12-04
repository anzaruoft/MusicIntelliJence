package interface_adapter.other_profile;

import use_case.other_profile.OtherProfileInputBoundary;
import use_case.other_profile.OtherProfileInputData;

/**
 * Controller for the Other Profile Use Case.
 */
public class OtherProfileController {

    private final OtherProfileInputBoundary otherProfileInteractor;

    public OtherProfileController(OtherProfileInputBoundary otherProfileInteractor) {
        this.otherProfileInteractor = otherProfileInteractor;
    }

    /**
     * Executes the Other Profile Use Case.
     * @param otherUsername the username of the other profile to view.
     * @param thisUsername the username of the user of the application.
     */
    public void execute(String otherUsername, String thisUsername) {
        final OtherProfileInputData otherProfileInputData = new OtherProfileInputData(thisUsername, otherUsername);
        System.out.println(thisUsername + "this username OP controller");
        otherProfileInteractor.execute(otherProfileInputData);
    }

    /**
     * Executes the "switch to LoggedInView" Use Case.
     */
    public void switchToFeedView() {
        otherProfileInteractor.switchToFeedView();
    }
}
