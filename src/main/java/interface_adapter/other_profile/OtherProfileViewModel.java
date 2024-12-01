package interface_adapter.other_profile;

import interface_adapter.ViewModel;

/**
 * The View Model for the Other Friend Profile View.
 */

public class OtherProfileViewModel extends ViewModel<OtherProfileState> {
    public static final String TITLE_LABEL = "Other Profile View";
    public static final String PROFILE_TITLE = "User Account";
    public static final String FOLLOWERS_COUNT_LABEL = "Followers = ";

    public OtherProfileViewModel() {
        super("other profile");
        setState(new OtherProfileState());
    }
}
