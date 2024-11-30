package use_case.feed;

import entity.User;

import java.util.List;

public interface FeedUserDataAccessInterface {

    /**
     * Checks if the given username has a profile.
     * @param username the username to load profile
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Returns the user with the given username.
     * @param username the username to look up
     * @return the user with the given username
     */
    User get(String username);

    /**
     * Returns the friends posts of the user.
     * @param friends
     * @return the friends posts of the user.
     */
    List<String> getFriendsPosts(List<String> friends);
}

