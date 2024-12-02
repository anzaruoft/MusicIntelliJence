package interface_adapter.feed;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * The State information representing the feed user.
 */
public class FeedState {
    private String username = "";
    private JSONArray posts = new JSONArray();
    private String feedError;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public JSONArray getPosts() {
        return posts;
    }

    public void setPosts(JSONArray posts) {
        this.posts = posts;
    }

    public String getFeedError() {
        return feedError;
    }

    public void setFeedError(String feedError) {
        this.feedError = feedError;
    }

    public int getPostsnumber() {
        return posts.length();
    }
}
