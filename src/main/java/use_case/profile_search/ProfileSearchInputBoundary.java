package use_case.profile_search;

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
