package use_case.feed;

public interface FeedInputBoundary {

    /**
     * Execute the Feed Use Case.
     * @param feedInputData the input data for this use case
     */
    void execute(FeedInputData feedInputData);

    /**
     * This is switchToChangePasswordView.
     */
    void switchToChangePasswordView();

    /**
     * This is switchToProfileView.
     * @param username username.
     */
    void switchToProfileView(String username);

    /**
     * This is switchToProfileSearchView.
     * @param username username.
     */
    void switchToProfileSearchView(String username);

    /**
     * This is switchToSong SearchView.
     * @param username username.
     */
    void switchToSongSearchView(String username);
}

