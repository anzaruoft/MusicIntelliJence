package use_case.leave_rating;

import entity.User;

public interface LeaveRatingUserDataAccessInterface {

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
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);

    void updateUserPosts(User user);
}
