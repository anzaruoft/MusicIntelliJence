package use_case.profile_search;

/**
 * The Output Boundary for the Profile Search Use Case.
 */

public interface ProfileSearchOutputBoundary {
    /**
     * Prepares the success view for the Profile Search Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ProfileSearchOutputData outputData);

    /**
     * Prepares the failure view for the Profile Search Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Prepares the Feed View for the Profile Search Use Case.
     */
    void switchToFeedView();

}
