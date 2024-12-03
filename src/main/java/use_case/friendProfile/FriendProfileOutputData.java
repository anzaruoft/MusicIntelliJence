package use_case.friendProfile;

import org.json.JSONArray;

import java.util.List;

/**
 * Output Data for the Friend Profile Use Case.
 */
public class FriendProfileOutputData {

    private final String username;
    private final JSONArray posts;
    private final JSONArray friends;

    public FriendProfileOutputData(String username, JSONArray posts, JSONArray topSongs, JSONArray friends) {
        this.username = username;
        this.posts = posts;
        this.friends = friends;
    }

    public String getUsername() {
        return username;
    }

    public JSONArray getPosts() {
        return posts;
    }

    public JSONArray getFriends() {
        return friends;
    }
}
