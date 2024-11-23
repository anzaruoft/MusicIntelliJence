package interface_adapter.friends;

import interface_adapter.ViewModel;

/**
 * The View Model for the Friends View.
 */
public class FriendsViewModel extends ViewModel<FriendsState> {

    public static final String TITLE_LABEL = "Friends List";

    public FriendsViewModel() {
        super("friends");
        setState(new FriendsState());
    }
}
