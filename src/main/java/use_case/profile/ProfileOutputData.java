package use_case.profile;

import entity.User;
import org.json.JSONArray;

import java.util.List;

/**
 * Output Data for the Profile Use Case.
 */
public class ProfileOutputData {

    private final String username;
    private final JSONArray friends;
    private final JSONArray posts;
    private final User user;

    public ProfileOutputData(String username, JSONArray friends, JSONArray posts, User user) {
        this.username = username;
        this.friends = friends;
        this.posts = posts;
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public JSONArray getFriends() {
        return friends;
    }

    public JSONArray getPosts() {
        return posts;
    }

    public User getUser() {
        return user;
    }
}
