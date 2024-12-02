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
    private final JSONArray topSongs;
    private final User user;

    public ProfileOutputData(String username, JSONArray friends, JSONArray posts, JSONArray topSongs, User user) {
        this.username = username;
        this.friends = friends;
        this.posts = posts;
        this.topSongs = topSongs;
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

    public JSONArray getTopSongs() {
        return topSongs;
    }

    public User getUser() {
        return user;
    }
}
