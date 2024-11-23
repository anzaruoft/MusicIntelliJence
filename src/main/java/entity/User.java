package entity;

import java.util.List;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Returns the email of the user.
     * @return the email of the user.
     */
    String getEmail();

    /**
     * Returns the posts of the user.
     * @return the posts of the user.
     */
    List<String> getPosts();

    /**
     * Returns the top songs of the user.
     * @return the topSongs of the user.
     */
    List<String> getTopSongs();

    /**
     * Returns the friends of the user.
     * @return the friends of the user.
     */
    List<String> getFriends();

    /**
     * Sets the friends of the user.
     * @param friends the friends of the user
     */
    void setFriends(List<String> friends);

    /**
     * Sets the posts of the user.
     * @param posts the posts of the user
     */
    void setPosts(List<String> posts);

    /**
     * Sets the top songs of the user.
     * @param topSongs the top songs of the user
     */
    void setTopSongs(List<String> topSongs);

}
