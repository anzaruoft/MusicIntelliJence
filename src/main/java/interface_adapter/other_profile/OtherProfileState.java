package interface_adapter.other_profile;

/**
 * The state for the Other Profile View Model.
 * Like Instagram, not all info is displayed as they are not friends yet.
 */
public class OtherProfileState {
    private String searchedUsername;
    private String thisUsername;
    private int friendsCount;
    private String usernameError;
    private String response;

    public String getSearchedUsername() {
        return searchedUsername;
    }

    public String getThisUsername() {
        return thisUsername;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getResponse() {
        return response;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setSearchedUsername(String otherUsername) {
        this.searchedUsername = otherUsername;
    }

    public void setThisUsername(String thisUsername) {
        this.thisUsername = thisUsername;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }
}
