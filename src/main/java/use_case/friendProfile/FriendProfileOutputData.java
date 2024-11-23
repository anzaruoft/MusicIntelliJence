package use_case.friendProfile;

import java.util.List;

/**
 * Output Data for the Friend Profile Use Case.
 */
public class FriendProfileOutputData {

    private final String username;
    private final List<String> posts;
    private final List<String> topSongs;
    private final List<String> friends;

    public FriendProfileOutputData(String username, List<String> posts, List<String> topSongs, List<String> friends) {
        this.username = username;
        this.posts = posts;
        this.topSongs = topSongs;
        this.friends = friends;
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

    public List<String> getFriends() {
        return friends;
    }
}
