package interface_adapter.profile;


import java.util.ArrayList;
import java.util.List;

/**
 * The state for the Profile View Model.
 */
public class ProfileState {
    private String username;
    private List<String> friends = new ArrayList<>();
    private List<String> topSongs = new ArrayList<>();
    private List<String> posts = new ArrayList<>();
    private String profileError;

    public String getUsername() {
        return username;
    }

    public int getFriendsNumber() {
        return friends.size();
    }

    public List<String> getTopSongs() {
        return topSongs;
    }

    public List<String> getPosts() {
        return posts;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public void setTopSongs(List<String> topSongs) {
        this.topSongs = topSongs;
    }

    public void setPosts(List<String> posts) {
        this.posts = posts;
    }

    public void setProfileError(String profileError) {
        this.profileError = profileError;
    }

}
