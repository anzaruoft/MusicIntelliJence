package use_case.friendProfile;

import java.util.List;

/**
 * Output Data for the Friend Profile Use Case.
 */
public class FriendProfileOutputData {

    private final String username;
    private final List<String> posts;
    private final List<String> topSongs;

    public FriendProfileOutputData(String username, List<String> posts, List<String> topSongs) {
        this.username = username;
        this.posts = posts;
        this.topSongs = topSongs;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getPosts() {
        return posts;
    }

    public List<String> getTopSongs() {
        return topSongs;
    }
}
