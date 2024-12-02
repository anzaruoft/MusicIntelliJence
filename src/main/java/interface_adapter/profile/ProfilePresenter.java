package interface_adapter.profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.friends.FriendsState;
import interface_adapter.friends.FriendsViewModel;
import use_case.profile.ProfileOutputBoundary;
import use_case.profile.ProfileOutputData;

/**
 * The Presenter for the Profile use Case.
 */
public class ProfilePresenter implements ProfileOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ProfileViewModel profileViewModel;
    private final FeedViewModel feedViewModel;
    private final FriendsViewModel friendsViewModel;

    public ProfilePresenter(ViewManagerModel viewManagerModel,
                            ProfileViewModel profileViewModel,
                            FeedViewModel feedViewModel,
                            FriendsViewModel friendsViewModel
                            ) {
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
        this.feedViewModel = feedViewModel;
        this.friendsViewModel = friendsViewModel;
    }

    @Override
    public void prepareSuccessView(ProfileOutputData response) {
        final ProfileState profileState = profileViewModel.getState();
        profileState.setUsername(response.getUsername());
        profileState.setUser(response.getUser());
        profileState.setFriends(response.getFriends());
        profileState.setPosts(response.getPosts());
        profileState.setTopSongs(response.getTopSongs());
        profileViewModel.setState(profileState);
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        final ProfileState profileState = profileViewModel.getState();
        profileState.setFriendsError(error);
        profileViewModel.firePropertyChanged();
    }

    @Override
    public void switchToFriendsView() {
        final FriendsState friendsState = friendsViewModel.getState();
        friendsViewModel.setState(friendsState);
        viewManagerModel.setState(friendsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToFeedView() {
        viewManagerModel.setState(feedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
