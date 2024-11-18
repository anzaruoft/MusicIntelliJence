package use_case.feed;

public interface FeedOutputBoundary {
    /**
     * Prepares the success view for the Feed Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(FeedOutputData outputData);

    /**
     * Prepares the failure view for the Feed Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
