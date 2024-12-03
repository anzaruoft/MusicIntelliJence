package interface_adapter.friends;

import interface_adapter.ViewManagerModel;
import interface_adapter.friendProfile.FriendProfileState;
import interface_adapter.friendProfile.FriendProfileViewModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.friends.FriendsOutputBoundary;
import use_case.friends.FriendsOutputData;

/**
 * The Presenter for the Friends Use Case.
 */
public class FriendsPresenter implements FriendsOutputBoundary {

    private final FriendsViewModel friendsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ProfileViewModel profileViewModel;
    private final FriendProfileViewModel friendProfileViewModel;

    public FriendsPresenter(ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel,
                            FriendsViewModel friendsViewModel,
                            FriendProfileViewModel friendProfileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
        this.friendsViewModel = friendsViewModel;
        this.friendProfileViewModel = friendProfileViewModel;
    }

    @Override
    public void prepareSuccessView(FriendsOutputData response) {
        final FriendsState friendsState = friendsViewModel.getState();
        this.friendsViewModel.setState(friendsState);
        this.friendsViewModel.firePropertyChanged();

        this.viewManagerModel.setState(friendsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void backToProfileView() {
        final ProfileState profileState = profileViewModel.getState();
        this.profileViewModel.setState(profileState);
        this.profileViewModel.firePropertyChanged();

        this.viewManagerModel.setState(profileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToFriendView(String friendUsername) {
        final FriendProfileState friendProfileState = friendProfileViewModel.getState();
        this.friendProfileViewModel.setState(friendProfileState);
        this.friendProfileViewModel.firePropertyChanged();

        this.viewManagerModel.setState(friendProfileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
