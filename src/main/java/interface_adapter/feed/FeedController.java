package interface_adapter.feed;

import use_case.feed.FeedInputBoundary;
import use_case.feed.FeedInputData;
/*

 */
public class FeedController {
    private final FeedInputBoundary feedInteractor;

    public FeedController(FeedInputBoundary feedInteractor) {
        this.feedInteractor = feedInteractor;
    }

    /**
     * Executes the Feed Use Case.
     */
    public void execute() {
        final FeedInputData feedInputData = new FeedInputData();

        feedInteractor.execute(feedInputData);
    }
}
