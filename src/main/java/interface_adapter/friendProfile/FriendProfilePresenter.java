package interface_adapter.friendProfile;

import interface_adapter.ViewManagerModel;
import interface_adapter.friends.FriendsViewModel;
import use_case.friendProfile.FriendProfileOutputBoundary;
import use_case.friendProfile.FriendProfileOutputData;

/**
 * The Presenter for the Friend Profile Use Case.
 */
public class FriendProfilePresenter implements FriendProfileOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final FriendsViewModel friendsViewModel;
    private final FriendProfileViewModel friendProfileViewModel;

    public FriendProfilePresenter(ViewManagerModel viewManagerModel, FriendProfileViewModel friendProfileViewModel,
                                  FriendsViewModel friendsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.friendProfileViewModel = friendProfileViewModel;
        this.friendsViewModel = friendsViewModel;
    }

    @Override
    public void prepareSuccessView(FriendProfileOutputData friendProfileOutputData) {
        final FriendProfileState friendProfileState = friendProfileViewModel.getState();
        friendProfileState.setFriends(friendProfileOutputData.getFriends());
        friendProfileState.setPosts(friendProfileOutputData.getPosts());
        friendProfileViewModel.setState(friendProfileState);
        friendProfileViewModel.firePropertyChanged();
        viewManagerModel.setState(friendProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToFriendsView() {
        viewManagerModel.setState(friendsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
