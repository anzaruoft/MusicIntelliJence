package use_case.profile_search;

import entity.User;

/**
 * DAO for Profile Search Use Case.
 */
public interface ProfileSearchUserDataAccessInterface {

    /**
     * Checks if the Searched User Exists.
     * @param username the username to check for.
     * @return True if user exists, false otherwise.
     */
    boolean existsByName(String username);

    /**
     * Returns the User with the given username.
     * @param username the username of the profile to load.
     * @return the user with the given username.
     */
    User get(String username);

}
