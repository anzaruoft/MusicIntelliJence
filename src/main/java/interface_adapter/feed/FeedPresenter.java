package interface_adapter.feed;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.song_search.SongSearchState;
import interface_adapter.song_search.SongSearchViewModel;
import use_case.feed.FeedOutputBoundary;
import use_case.feed.FeedOutputData;

/**
 * FeedPresenter class.
 */
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
        final FeedState feedState = feedViewModel.getState();
        feedState.setUsername(outputData.getUsername());
        feedState.setUser(outputData.getUser());
        feedState.setPosts(outputData.getPosts());
        feedViewModel.setState(feedState);

        viewManagerModel.setState(feedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final FeedState feedState = feedViewModel.getState();
        feedState.setFeedError(errorMessage);
        feedViewModel.setState(feedState);

        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void switchToProfileView(String username) {
        final ProfileState profileState = profileViewModel.getState();
        profileState.setUsername(username);
        this.profileViewModel.setState(profileState);
        this.profileViewModel.firePropertyChanged();
        this.profileViewModel.getState().setPosts(profileState.getPosts());
        // NEW ADDITION
        this.viewManagerModel.setState(profileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSongSearchView(String username) {
        final SongSearchState songSearchState = songSearchViewModel.getState();
        songSearchState.setUsername(username);
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
