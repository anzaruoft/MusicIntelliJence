package use_case.profile;

import entity.User;

/**
 * The Input Data for the Profile Use Case.
 */
public class ProfileInputData {

    private final String username;

    private final User user;

    public ProfileInputData(String username, User user) {
        this.username = username;
        this.user = user;
    }

    public String getName() {
        return username;
    }

    public User getUser() {
        return user;
    }
}
