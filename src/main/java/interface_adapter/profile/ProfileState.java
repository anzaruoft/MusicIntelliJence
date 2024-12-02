package interface_adapter.profile;

import org.json.JSONArray;

import entity.User;

/**
 * The state for the Profile View Model.
 */
public class ProfileState {
    private String username;
    private JSONArray friends = new JSONArray();
    private JSONArray topSongs = new JSONArray();
    private JSONArray posts = new JSONArray();
    private String friendsError;
    private User user;

    public String getUsername() {
        return username;
    }

    public int getFriendsNumber() {
        return friends.length();
    }

    public JSONArray getTopSongs() {
        return topSongs;
    }

    public JSONArray getPosts() {
        return posts;
    }

    public String getFriendsError() {
        return friendsError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFriends(JSONArray friends) {
        this.friends = friends;
    }

    public void setTopSongs(JSONArray topSongs) {
        this.topSongs = topSongs;
    }

    public void setPosts(JSONArray posts) {
        this.posts = posts;
    }

    public void setFriendsError(String friendsError) {
        this.friendsError = friendsError;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
