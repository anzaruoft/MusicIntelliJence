package interface_adapter.song_search;

import interface_adapter.ViewManagerModel;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.leave_rating.LeaveRatingViewModel;
import use_case.song_search.SongSearchOutputBoundary;
import use_case.song_search.SongSearchOutputData;

public class SongSearchPresenter implements SongSearchOutputBoundary {

    private SongSearchViewModel songSearchViewModel;
    private ViewManagerModel viewManagerModel;
    private FeedViewModel feedViewModel;
    private LeaveRatingViewModel leaveRatingViewModel;

    public SongSearchPresenter(ViewManagerModel viewManagerModel, SongSearchViewModel songSearchViewModel,
                               FeedViewModel feedViewModel, LeaveRatingViewModel leaveRatingViewModel) {
        this.songSearchViewModel = songSearchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.feedViewModel = feedViewModel;
        this.leaveRatingViewModel = leaveRatingViewModel;
    }

    @Override
    public void prepareSuccessView(SongSearchOutputData outputData) {
    }

    @Override
    public void prepareFailView(String errorMessage) {
    }

    @Override
    public void switchToFeedView() {
        viewManagerModel.setState(feedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLeaveRatingView() {
        this.viewManagerModel.setState(leaveRatingViewModel.getViewName());
        this.leaveRatingViewModel.getState().setSongTitle(this.songSearchViewModel.getState().getSongTitle());
        this.leaveRatingViewModel.firePropertyChanged();
        this.viewManagerModel.firePropertyChanged();
    }
}