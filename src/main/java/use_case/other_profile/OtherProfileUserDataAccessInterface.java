package use_case.other_profile;

import entity.User;

/**
 * DAO for the Other Profile Use Case
 */

public interface OtherProfileUserDataAccessInterface {

    /**
     * Checks if the given username has a profile.
     * @param otherUsername the username to load profile
     * @return true if a otheruser with the given username exists; false otherwise
     */
    boolean existsByName(String otherUsername);

    /**
     * Returns the other user with the given username.
     * @param otherUsername the otheer username to look up
     * @return the other user with the given username
     */
    User get(String otherUsername);

    boolean isFriends(String thisUsername, String otherUsername);

    void updateUserFriends(User otherUser, User thisUser);

}