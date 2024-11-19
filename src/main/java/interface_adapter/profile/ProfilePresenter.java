package interface_adapter.profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.profile.ProfileOutputBoundary;
import use_case.profile.ProfileOutputData;

/**
 * The Presenter for the Profile use Case.
 */
public class ProfilePresenter implements ProfileOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ProfileViewModel profileViewModel;
    private final FeedViewModel feedViewModel;

    public ProfilePresenter(ViewManagerModel viewManagerModel,
                            ProfileViewModel profileViewModel,
                            FeedViewModel feedViewModel
                            ) {
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
        this.feedViewModel = feedViewModel;

    }

    @Override
    public void prepareSuccessView(ProfileOutputData profileOutputData) {
        final ProfileState profileState = profileViewModel.getState();
        profileState.setFriends(profileOutputData.getFriends());
        profileState.setPosts(profileOutputData.getPosts());
        profileState.setTopSongs(profileOutputData.getTopSongs());
        profileViewModel.setState(profileState);
        profileViewModel.firePropertyChanged();
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final ProfileState profileState = profileViewModel.getState();
        profileState.setProfileError(error);
        profileViewModel.firePropertyChanged();
    }

    @Override
    public void switchToFeedView() {
        viewManagerModel.setState(feedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
