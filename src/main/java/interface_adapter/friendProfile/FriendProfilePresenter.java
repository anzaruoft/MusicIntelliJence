package interface_adapter.friendProfile;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.friendProfile.FriendProfileViewModel;
import use_case.friendProfile.FriendProfileInputBoundary;
import use_case.profile.ProfileOutputData;

/**
 *
 */
public class FriendProfilePresenter implements FriendProfileInputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ProfileViewModel profileViewModel;
    private final FriendProfileViewModel friendProfileViewModel;

    public FriendProfilePresenter(ViewManagerModel viewManagerModel, FriendProfileViewModel friendProfileViewModel, ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.friendProfileViewModel = friendProfileViewModel;
        this.profileViewModel = profileViewModel;
    }

    @Override
    public void prepareSuccessView(ProfileOutputData profileOutputData) {
        final FriendProfileState friendProfileState = friendProfileViewModel.getState();
        friendProfileState.setFollowers(profileOutputData.getFriends());
        friendProfileState.setPosts(profileOutputData.getPosts());
        friendProfileState.setTopSongs(profileOutputData.getTopSongs());
        friendProfileViewModel.setState(friendProfileState);
        friendProfileViewModel.firePropertyChanged();
        viewManagerModel.setState(friendProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final FriendProfileState friendProfileState = friendProfileViewModel.getState();
        friendProfileState.setProfileError(error);
        profileViewModel.firePropertyChanged();
    }

    @Override
    public void switchToFriendsView() {
        viewManagerModel.setState(friendProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
