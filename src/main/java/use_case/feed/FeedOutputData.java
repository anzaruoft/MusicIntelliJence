package use_case.feed;

import entity.User;
import org.json.JSONArray;
import data_access.InMemoryUserDataAccessObject;


public class FeedOutputData {
    // The username of the user whose feed is being prepared
    private final String username;
    // The list of posts from friends
    private final JSONArray posts;
    private final User user;
    private InMemoryUserDataAccessObject inMemoryUserDataAccessObject;

    public FeedOutputData(String username, JSONArray posts, User user,
                          InMemoryUserDataAccessObject inMemoryUserDataAccessObject) {
        this.username = username;
        this.posts = posts;
        this.user = user;
        // Added
        this.inMemoryUserDataAccessObject = inMemoryUserDataAccessObject;
    }

//    public String getUsername() {
//        return username;
//    }
    public String getUsername() {
        return inMemoryUserDataAccessObject.getCurrentUsername();
    }

    public JSONArray getPosts() {
        return posts;
    }

    public User getUser() {
        return user;
    }
}