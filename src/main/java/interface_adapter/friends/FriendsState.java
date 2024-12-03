package interface_adapter.friends;

import entity.User;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * The state for the Friends View Model.
 */
public class FriendsState {
    private String username = "";
    private JSONArray friends = new JSONArray();
    private User user;

    public String getUsername() {
        return username;
    }

    public JSONArray getFriends() {
        return friends;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFriends(JSONArray friends) {
        this.friends = friends;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
