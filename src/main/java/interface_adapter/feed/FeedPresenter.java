package interface_adapter.feed;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.song_search.SongSearchState;
import interface_adapter.song_search.SongSearchViewModel;
import use_case.change_password.ChangePasswordInputData;
import use_case.change_password.ChangePasswordInteractor;
import use_case.feed.FeedOutputBoundary;
import use_case.feed.FeedOutputData;

import javax.swing.text.View;

public class FeedPresenter implements FeedOutputBoundary {

    private FeedViewModel feedViewModel;
    private ViewManagerModel viewManagerModel;
    private ProfileViewModel profileViewModel;
    private SongSearchViewModel songSearchViewModel;
    private LoggedInViewModel changePasswordViewModel;

    public FeedPresenter(ViewManagerModel viewManagerModel, FeedViewModel feedViewModel,
                         ProfileViewModel profileViewModel, SongSearchViewModel songSearchViewModel,
                         LoggedInViewModel changePasswordViewModel) {
        this.feedViewModel = feedViewModel;
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
        this.songSearchViewModel = songSearchViewModel;
        this.changePasswordViewModel = changePasswordViewModel;
    }

    @Override
    public void prepareSuccessView(FeedOutputData outputData) {
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    @Override
    public void switchToProfileView(String username) {
        final ProfileState profileState = profileViewModel.getState();
        // feedState.setUsername(response.getUsername());
        this.profileViewModel.setState(profileState);
        this.profileViewModel.firePropertyChanged();

        this.viewManagerModel.setState(profileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSongSearchView(String username) {
        final SongSearchState songSearchState = songSearchViewModel.getState();
        // feedState.setUsername(response.getUsername());
        this.songSearchViewModel.setState(songSearchState);
        this.songSearchViewModel.firePropertyChanged();

        this.viewManagerModel.setState(songSearchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToChangePasswordView() {
        final LoggedInState loggedInState = changePasswordViewModel.getState();
        // feedState.setUsername(response.getUsername());
        this.changePasswordViewModel.setState(loggedInState);
        this.changePasswordViewModel.firePropertyChanged();

        this.viewManagerModel.setState(changePasswordViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }
}

    // public void switchtoChangePasswordView(String username, String newpassword, String email) {
       // ChangePasswordInputData changePasswordInputData = new ChangePasswordInteractor(username, newpassword, email)




