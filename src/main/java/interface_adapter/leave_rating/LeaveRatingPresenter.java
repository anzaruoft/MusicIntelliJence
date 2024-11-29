package interface_adapter.leave_rating;

import interface_adapter.ViewManagerModel;
import interface_adapter.song_search.SongSearchViewModel;
import use_case.leave_rating.LeaveRatingOutputBoundary;
import use_case.leave_rating.LeaveRatingOutputData;
import view.SongSearchView;

public class LeaveRatingPresenter implements LeaveRatingOutputBoundary {

    private LeaveRatingViewModel leaveRatingViewModel;
    private ViewManagerModel viewManagerModel;
    private SongSearchViewModel songSearchViewModel;

    public LeaveRatingPresenter(ViewManagerModel viewManagerModel, LeaveRatingViewModel leaveRatingViewModel, SongSearchViewModel songSearchViewModel) {
        this.leaveRatingViewModel = leaveRatingViewModel;
        this.viewManagerModel = viewManagerModel;
        this.songSearchViewModel = songSearchViewModel;
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
}
