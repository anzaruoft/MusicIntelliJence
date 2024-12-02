package interface_adapter.feed;

import interface_adapter.ViewModel;

/**
 * FeedViewModel class.
 */
public class FeedViewModel extends ViewModel<FeedState> {
    public FeedViewModel() {
        super("feed");
        setState(new FeedState());
    }
}
