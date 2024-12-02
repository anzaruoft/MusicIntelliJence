package use_case.profile_search;

/**
 * The Input Data for the Profile Search Use Case.
 */

public class ProfileSearchInputData {
    private final String inputUsername;
    private final String thisUsername;

    public ProfileSearchInputData(String input_username, String this_username) {
        this.inputUsername = input_username;
        this.thisUsername = this_username;
    }

    public String getSearchedUsername() {
        return inputUsername;
    }

    public String getThisUsername() {
        return thisUsername;
    }
}
