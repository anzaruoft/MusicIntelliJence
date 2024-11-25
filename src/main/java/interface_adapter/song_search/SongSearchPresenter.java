package interface_adapter.song_search;

import interface_adapter.ViewManagerModel;
import interface_adapter.feed.FeedState;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.leave_rating.LeaveRatingState;
import interface_adapter.leave_rating.LeaveRatingViewModel;
import interface_adapter.profile.ProfileState;
import use_case.song_search.SongSearchOutputBoundary;
import use_case.song_search.SongSearchOutputData;

public class SongSearchPresenter implements SongSearchOutputBoundary {

    private SongSearchViewModel songSearchViewModel;
    private ViewManagerModel viewManagerModel;
    private FeedViewModel feedViewModel;
    private LeaveRatingViewModel leaveRatingViewModel;

    public SongSearchPresenter(ViewManagerModel viewManagerModel, SongSearchViewModel songSearchViewModel,
                               FeedViewModel feedViewModel) {
        this.songSearchViewModel = songSearchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.feedViewModel = feedViewModel;
    }

    @Override
    public void prepareSuccessView(SongSearchOutputData outputData) {
    }

    @Override
    public void prepareFailView(String errorMessage) {
    }

    @Override
    public void switchToFeedView(String username) {
        final FeedState feedState = feedViewModel.getState();
        this.feedViewModel.setState(feedState);
        this.feedViewModel.firePropertyChanged();

        viewManagerModel.setState(feedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLeaveRatingView(String username) {
        final LeaveRatingState leaveRatingState = leaveRatingViewModel.getState();
        // feedState.setUsername(response.getUsername());
        this.leaveRatingViewModel.setState(leaveRatingState);
        this.leaveRatingViewModel.firePropertyChanged();

        this.viewManagerModel.setState(leaveRatingViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareFeedView() {
        final FeedState feedState = feedViewModel.getState();
        this.feedViewModel.setState(feedState);
        this.feedViewModel.firePropertyChanged();

        this.viewManagerModel.setState(feedViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}