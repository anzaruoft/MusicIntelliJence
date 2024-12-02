package interface_adapter.leave_rating;

import interface_adapter.ViewManagerModel;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.song_search.SongSearchViewModel;
import use_case.leave_rating.LeaveRatingOutputBoundary;
import use_case.leave_rating.LeaveRatingOutputData;

/**
 * This is the LeaveRatingPresenter class.
 */
public class LeaveRatingPresenter implements LeaveRatingOutputBoundary {

    private LeaveRatingViewModel leaveRatingViewModel;
    private ViewManagerModel viewManagerModel;
    private SongSearchViewModel songSearchViewModel;
    private FeedViewModel feedViewModel;

    public LeaveRatingPresenter(ViewManagerModel viewManagerModel, LeaveRatingViewModel leaveRatingViewModel,
                                SongSearchViewModel songSearchViewModel, FeedViewModel feedViewModel) {
        this.leaveRatingViewModel = leaveRatingViewModel;
        this.viewManagerModel = viewManagerModel;
        this.songSearchViewModel = songSearchViewModel;
        this.feedViewModel = feedViewModel;
    }

    @Override
    public void prepareSuccessView(LeaveRatingOutputData outputData) {
    }

    @Override
    public void prepareFailView(String errorMessage) {
    }

    @Override
    public void switchToSongSearchView() {
        this.viewManagerModel.setState(songSearchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToFeedView() {
        this.viewManagerModel.setState(feedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

