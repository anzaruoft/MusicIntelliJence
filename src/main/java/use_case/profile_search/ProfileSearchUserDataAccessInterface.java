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

    /**
     * Returns the username of the curren user of the application.
     * @return the username of the current user; null indicates that no one is logged into the application.
     */
    String getCurrentUsername();

    /**
     * Sets the username indicating who is the current user of the application.
     * @param username the new current username; null to indicate that no one is currently logged into the application.
     */
    void setCurrentUsername(String username);

    /**
     * Sets the username being searched.
     * @param inputUsername the searched username; null to indicate that no one was searched.
     */
    void setSearchedUsername(String inputUsername);
}
