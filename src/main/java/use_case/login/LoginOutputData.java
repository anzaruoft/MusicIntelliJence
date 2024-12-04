package use_case.login;

import data_access.InMemoryUserDataAccessObject;
/**
 * Output Data for the Login Use Case.
 */

public class LoginOutputData {

    private final String username;
    private final boolean useCaseFailed;
    private InMemoryUserDataAccessObject inMemoryUserDataAccessObject;

    public LoginOutputData(String username, boolean useCaseFailed,
                           InMemoryUserDataAccessObject inMemoryUserDataAccessObject) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.inMemoryUserDataAccessObject = inMemoryUserDataAccessObject;
    }

    public String getUsername() {
        return inMemoryUserDataAccessObject.getCurrentUsername();
    }

}
