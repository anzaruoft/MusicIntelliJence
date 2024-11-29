package use_case.other_profile;

/**
 * The Output Boundary for the Other Profile Use Case.
 */
public interface OtherProfileOutputBoundary {
    /**
     * Prepares the success view for the Other Profile Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(OtherProfileOutputData outputData);

    /**
     * Prepares the fail view for the Other Profile Use Case.
     * @param otherUsername the username for the other user data
     */
    void prepareFailView(String otherUsername);

    /**
     * Switches to the Feed View.
     */
    void switchToFeedView();

    // potentially add switch to profile Search View when implemented?
}
