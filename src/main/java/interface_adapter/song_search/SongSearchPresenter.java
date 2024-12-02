package interface_adapter.song_search;

import interface_adapter.ViewManagerModel;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.leave_rating.LeaveRatingState;
import interface_adapter.leave_rating.LeaveRatingViewModel;
import use_case.song_search.SongSearchOutputBoundary;
import use_case.song_search.SongSearchOutputData;

/**
 * This is the SongSearchPresenter class.
 */
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
        this.leaveRatingViewModel.getState().setUsername(this.songSearchViewModel.getState().getUsername());
        this.leaveRatingViewModel.getState().setSongTitle(this.songSearchViewModel.getState().getSongTitle());
        this.leaveRatingViewModel.getState().setRating(this.songSearchViewModel.getState().getRating());
        this.leaveRatingViewModel.firePropertyChanged();
        this.viewManagerModel.firePropertyChanged();
    }
}
