package use_case.profile_search;

/**
 * The Input Boundary for the Profile Search Use Case.
 */

public interface ProfileSearchInputBoundary {
    /**
     * Execute the Profile Search Use Case.
     * @param profileSearchInputData the input data for this use case
     */
    void execute(ProfileSearchInputData profileSearchInputData);

    /**
     * Execute the back to Feed View Use Case.
     */
    void switchToFeedView();
}
