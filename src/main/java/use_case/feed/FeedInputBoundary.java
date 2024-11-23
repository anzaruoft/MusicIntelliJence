package use_case.feed;

public interface FeedInputBoundary {

    /**
     * Execute the Feed Use Case.
     * @param feedInputData the input data for this use case
     */
    void execute(FeedInputData feedInputData);

}

