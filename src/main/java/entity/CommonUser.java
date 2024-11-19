package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private String name;
    private String password;
    private String email;
    private List<String> posts = new ArrayList<>();
    private List<String> topSongs = new ArrayList<>();
    private List<String> friends = new ArrayList<>();

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
    public List<String> getPosts() {
        return posts;
    }

    @Override
    public List<String> getTopSongs() {
        return topSongs;
    }

    @Override
    public List<String> getFriends() {
        return friends;
    }

    @Override
    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    @Override
    public void setPosts(List<String> posts) {
        this.posts = posts;
    }

    @Override
    public void setTopSongs(List<String> topSongs) {
        this.topSongs = topSongs;
    }

}
