package interface_adapter.feed;

import interface_adapter.ViewModel;

public class FeedViewModel extends ViewModel<FeedState> {
    public FeedViewModel() {
        super("feed");
        setState(new FeedState());
    }
}
