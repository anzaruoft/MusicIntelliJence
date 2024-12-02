package use_case.feed;

import org.json.JSONArray;

import java.util.List;

public class FeedOutputData {
    // The username of the user whose feed is being prepared
    private final String username;
    // The list of posts from friends
    private final JSONArray posts;

    public FeedOutputData(String username, JSONArray posts) {
        this.username = username;
        this.posts = posts;
    }

    public String getUsername() {
        return username;
    }

    public JSONArray getPosts() {
        return posts;
    }
}