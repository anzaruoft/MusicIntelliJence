package entity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private String name;
    private String password;
    private String email;
    private JSONArray posts = new JSONArray();
    private JSONArray topSongs = new JSONArray();
    private JSONArray friends = new JSONArray();
    private JSONArray receivedFriends = new JSONArray();
    private JSONArray sentFriends = new JSONArray();
    private JSONArray ratings = new JSONArray();

    public CommonUser(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public JSONArray getPosts() {
        return posts;
    }

    @Override
    public JSONArray getTopSongs() {
        return topSongs;
    }

    @Override
    public JSONArray getFriends() {
        return friends;
    }

    @Override
    public JSONArray getSentFriends() {
        return sentFriends;
    }

    @Override
    public JSONArray getReceivedFriends() {
        return receivedFriends;
    }

    @Override
    public JSONArray getRatings() {
        return ratings;
    }

    @Override
    public void setFriends(JSONArray friends) {
        this.friends = friends;
    }

    @Override
    public void setPosts(JSONArray posts) {
        this.posts = posts;
    }

    @Override
    public void setRatings(JSONArray ratings) {
        this.ratings = ratings;
    }

    @Override
    public void setTopSongs(JSONArray topSongs) {
        this.topSongs = topSongs;
    }

    @Override
    public void setSentFriends(JSONArray sentFriends) {
        this.sentFriends = sentFriends;
    }

    @Override
    public void setReceivedFriends(JSONArray receivedFriends) {
        this.receivedFriends = receivedFriends;
    }


}
