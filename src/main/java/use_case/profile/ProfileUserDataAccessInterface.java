package use_case.profile;

import entity.User;

public interface ProfileUserDataAccessInterface {

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

}
