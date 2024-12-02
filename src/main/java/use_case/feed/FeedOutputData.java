package use_case.feed;

import entity.User;
import org.json.JSONArray;


public class FeedOutputData {
    // The username of the user whose feed is being prepared
    private final String username;
    // The list of posts from friends
    private final JSONArray posts;
    private final User user;

    public FeedOutputData(String username, JSONArray posts, User user) {
        this.username = username;
        this.posts = posts;
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public JSONArray getPosts() {
        return posts;
    }

    public User getUser() {
        return user;
    }
}