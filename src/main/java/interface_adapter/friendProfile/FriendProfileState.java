package interface_adapter.friendProfile;

import org.json.JSONArray;

/**
 * The state for the Friend Profile View Model.
 */
public class FriendProfileState {
    private String friendUsername;
    private JSONArray friends = new JSONArray();
    private JSONArray friendsSent = new JSONArray();
    private JSONArray friendsReceived = new JSONArray();
    private JSONArray friendTopSongs = new JSONArray();
    private JSONArray friendPosts = new JSONArray();
    private String usernameError;

    public String getUsername() {
        return friendUsername;
    }

    public int getFriendsNumber() {
        return friends.length();
    }

    public JSONArray getFriendsSent() {
        return friendsSent;
    }

    public JSONArray getFriendsReceived() {
        return friendsReceived;
    }

    public JSONArray getFriends() {
        return friends;
    }

    public JSONArray getTopSongs() {
        return friendTopSongs;
    }

    public JSONArray getPosts() {
        return friendPosts;
    }

    public String getProfileError() {
        return usernameError;
    }

    public void setFriends(JSONArray followers) {
        this.friends = followers;
    }

    public void setFriendsSent(JSONArray friendsSent) {
        this.friendsSent = friendsSent;
    }

    public void setFriendsReceived(JSONArray friendsReceived) {
        this.friendsReceived = friendsReceived;
    }

    public void setTopSongs(JSONArray topSongs) {
        this.friendTopSongs = topSongs;
    }

    public void setPosts(JSONArray posts) {
        this.friendPosts = posts;
    }

    public void setProfileError(String usernameError) {
        this.usernameError = usernameError;
    }
    // Checkstyle error here?
}
