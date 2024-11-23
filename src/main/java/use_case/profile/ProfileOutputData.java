package use_case.profile;

import java.util.List;

/**
 * Output Data for the Profile Use Case.
 */
public class ProfileOutputData {

    private final String username;
    private final List<String> friends;
    private final List<String> posts;
    private final List<String> topSongs;

    public ProfileOutputData(String username, List<String> friends, List<String> posts, List<String> topSongs) {
        this.username = username;
        this.friends = friends;
        this.posts = posts;
        this.topSongs = topSongs;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getFriends() {
        return friends;
    }

    public List<String> getPosts() {
        return posts;
    }

    public List<String> getTopSongs() {
        return topSongs;
    }
}
