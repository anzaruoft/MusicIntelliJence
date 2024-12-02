package use_case.feed;

/**
 * The Feed Use Case.
 */
public interface FeedInputBoundary {

    /**
     * Execute the Feed Use Case.
     * @param feedInputData the input data for this use case
     */
    void execute(FeedInputData feedInputData);

    /**
     * Prepares to switch to the changePasswordView.
     */
    void switchToChangePasswordView();
}

