package interface_adapter.profile;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Profile View.
 */
public class ProfileViewModel extends ViewModel<ProfileState> {
    // WRITE ALL OF THE WORDS THAT WILL BE ADDED TO THE VIEW
    public static final String TITLE_LABEL = "Profile View";
    public static final String FOLLOWERS_LABEL = "Followers: ";
    public static final String FOLLOWING_LABEL = "Following: ";
    public static final String POSTS_LABEL = "Posts: ";
    public static final String TOP_SONGS_LABEL = "Top Songs by Rating: ";

    public ProfileViewModel() {
        super("Profile");
        setState(new ProfileState());
    }
}
