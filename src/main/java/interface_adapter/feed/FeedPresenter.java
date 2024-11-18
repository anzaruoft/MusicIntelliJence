package interface_adapter.feed;

import interface_adapter.ViewManagerModel;
import use_case.feed.FeedOutputBoundary;
import use_case.feed.FeedOutputData;

import javax.swing.text.View;

public class FeedPresenter implements FeedOutputBoundary {

    private FeedViewModel feedViewModel;
    private ViewManagerModel viewManagerModel;

    public FeedPresenter(ViewManagerModel viewManagerModel, FeedViewModel feedViewModel) {
        this.feedViewModel = feedViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(FeedOutputData outputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
