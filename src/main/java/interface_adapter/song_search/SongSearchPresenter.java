package interface_adapter.song_search;

import interface_adapter.ViewManagerModel;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.leave_rating.LeaveRatingState;
import interface_adapter.leave_rating.LeaveRatingViewModel;
import interface_adapter.login.LoginState;
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
        // On success, switch to the leave rating view WITH THE SONG NAME THERE.
        final LeaveRatingState leaveRatingState = leaveRatingViewModel.getState();
        leaveRatingState.setSongTitle(outputData.getSongTitle());
        this.leaveRatingViewModel.setState(leaveRatingState);
        leaveRatingViewModel.firePropertyChanged();

        viewManagerModel.setState(leaveRatingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
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
        this.viewManagerModel.firePropertyChanged();
    }
}
