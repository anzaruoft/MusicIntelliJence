package interface_adapter.other_profile;

/**
 * The state for the Other Profile View Model.
 * Like Instagram, not all info is displayed as they are not friends yet.
 */
public class OtherProfileState {
    private String otherUsername;
    private String username;
    private int friendsCount;
    private String usernameError;

    public String getOtherUsername() {
        return otherUsername;
    }

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setOtherUsername(String otherUsername) {
        this.otherUsername = otherUsername;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }
}
