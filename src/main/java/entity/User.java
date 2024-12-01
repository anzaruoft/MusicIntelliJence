package entity;

import org.json.JSONArray;

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
    JSONArray getPosts();

    /**
     * Returns the ratings by the user.
     * @return the ratings by the user.
     */
    JSONArray getRatings();

    /**
     * Returns the top songs of the user.
     * @return the topSongs of the user.
     */
    JSONArray getTopSongs();

    /**
     * Returns the friends of the user.
     * @return the friends of the user.
     */
    JSONArray getFriends();

    /**
     * Returns the sent friend requests of the user.
     * @return the sentFriends of the user.
     */
    JSONArray getSentFriends();

    /**
     * Returns the received friend requests of the user.
     * @return the receivedFriends of the user.
     */
    JSONArray getReceivedFriends();

    /**
     * Sets the friends of the user.
     * @param friends the friends of the user
     */
    void setFriends(JSONArray friends);

    /**
     * Sets the sent friends requests of the user.
     * @param sentFriends the sent friend requests of the user
     */
    void setSentFriends(JSONArray sentFriends);

    /**
     * Sets the received friends requests of the user.
     * @param receivedFriends the sent friend requests of the user
     */
    void setReceivedFriends(JSONArray receivedFriends);


    /**
     * Sets the posts by the user.
     * @param posts the posts by the user
     */
    void setPosts(JSONArray posts);

    /**
     * Sets the ratings by the user.
     * @param posts the posts by the user
     */
    void setRatings(JSONArray posts);

    /**
     * Sets the top songs of the user.
     * @param topSongs the top songs of the user
     */
    void setTopSongs(JSONArray topSongs);

}
