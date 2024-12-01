package use_case.feed;

import java.util.List;

public class FeedOutputData {
    // The username of the user whose feed is being prepared
    private final String username;
    // The list of posts from friends
    private final List<String> posts;

    public FeedOutputData(String username, List<String> posts) {
        this.username = username;
        this.posts = posts;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getPosts() {
        return posts;
    }
}