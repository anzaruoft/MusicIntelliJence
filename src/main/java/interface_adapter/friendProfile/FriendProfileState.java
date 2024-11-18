package interface_adapter.friendProfile;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FriendProfileState {
    private String friendUsername;
    private List<String> friends = new ArrayList<>();
    private List<String> friendsSent = new ArrayList<>();
    private List<String> friendsReceived = new ArrayList<>();
    private List<String> friendTopSongs = new ArrayList<>();
    private List<String> friendPosts = new ArrayList<>();
    private String usernameError;

    public String getUsername() {
        return friendUsername;
    }

    public int getFriendsNumber() {
        return friends.size();
    }

    public List<String> getFriendsSent() {
        return friendsSent;
    }

    public List<String> getFriendsReceived() {
        return friendsReceived;
    }

    public List<String> getFriends() {
        return friends;
    }

    public List<String> getTopSongs() {
        return friendTopSongs;
    }

    public List<String> getPosts() {
        return friendPosts;
    }

    public String getProfileError() {
        return usernameError;
    }

    public void setFriends(List<String> followers) {
        this.friends = followers;
    }

    public void setFriendsSent(List<String> friendsSent) {
        this.friendsSent = friendsSent;
    }

    public void setFriendsReceived(List<String> friendsReceived) {
        this.friendsReceived = friendsReceived;
    }

    public void setTopSongs(List<String> topSongs) {
        this.friendTopSongs = topSongs;
    }

    public void setPosts(List<String> posts) {
        this.friendPosts = posts;
    }

    public void setProfileError(String usernameError) {
        this.usernameError = usernameError;
    }
}
