package interface_adapter.friendProfile;

import interface_adapter.ViewModel;

/**
 * The View Model for the profile View.
 */
public class FriendProfileViewModel extends ViewModel<FriendProfileState> {

    public static final String PROFILE_TITLE = "User Account";

    public FriendProfileViewModel(String name) {
        super("friend profile");
        setState(new FriendProfileState());
    }

}
