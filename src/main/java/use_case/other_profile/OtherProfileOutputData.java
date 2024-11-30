package use_case.other_profile;

import java.util.List;

/**
 * Output Data for the Other Profile Use Case.
 */
public class OtherProfileOutputData {
    private final String otherUsername;
    private final List<String> friends;
    private final List<String> posts;
    private final List<String> topSongs;

    public OtherProfileOutputData(String otherUsername,
                                  List<String> friends,
                                  List<String> posts,
                                  List<String> topSongs) {
        this.otherUsername = otherUsername;
        this.friends = friends;
        this.posts = posts;
        this.topSongs = topSongs;
    }

    public String getOtherUsername() {
        return otherUsername;
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
