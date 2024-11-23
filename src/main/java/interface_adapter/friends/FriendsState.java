package interface_adapter.friends;

import java.util.ArrayList;
import java.util.List;

/**
 * The state for the Friends View Model.
 */
public class FriendsState {
    private String username = "";
    private List<String> friends = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

}
