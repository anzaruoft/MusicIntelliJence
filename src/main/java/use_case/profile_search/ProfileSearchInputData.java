package use_case.profile_search;

/**
 * The Input Data for the Profile Search Use Case.
 */

public class ProfileSearchInputData {
    private final String inputUsername;

    public ProfileSearchInputData(String input_username) {
        this.inputUsername = input_username;
    }

    public String getUsername() {
        return inputUsername;
    }
}
