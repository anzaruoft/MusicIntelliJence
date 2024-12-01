package use_case.profile;

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

    public ProfileOutputData(String username, JSONArray friends, JSONArray posts, JSONArray topSongs) {
        this.username = username;
        this.friends = friends;
        this.posts = posts;
        this.topSongs = topSongs;
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
}
