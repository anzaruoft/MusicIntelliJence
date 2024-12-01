package interface_adapter.other_profile;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * The state for the Other Profile View Model.
 * Like Instagram, not all info is displayed as they are not friends yet.
 */
public class OtherProfileState {
    private String otherUsername;
    private JSONArray friends = new JSONArray();
    private String usernameError;

    public String getOtherUsername() {
        return otherUsername;
    }

    public String getUsername() {
        return otherUsername;
    }

    public int getFriendsNumber() {
        return friends.length();
    }

    public String getProfileError() {
        return usernameError;
    }

    public void setOtherUsername(String otherUsername) {
        this.otherUsername = otherUsername;
    }

    public void setFriends(JSONArray friends) {
        this.friends = friends;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

}
