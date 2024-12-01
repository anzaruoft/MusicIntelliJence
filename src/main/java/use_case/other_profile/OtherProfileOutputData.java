package use_case.other_profile;

import org.json.JSONArray;

import java.util.List;

/**
 * Output Data for the Other Profile Use Case.
 */
public class OtherProfileOutputData {
    private final String otherUsername;
    private final JSONArray friends;
    private final JSONArray posts;
    private final JSONArray topSongs;

    public OtherProfileOutputData(String otherUsername,
                                  JSONArray friends,
                                  JSONArray posts,
                                  JSONArray topSongs) {
        this.otherUsername = otherUsername;
        this.friends = friends;
        this.posts = posts;
        this.topSongs = topSongs;
    }

    public String getOtherUsername() {
        return otherUsername;
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
