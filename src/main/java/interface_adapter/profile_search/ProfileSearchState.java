package interface_adapter.profile_search;

/**
 * The State for the Profile Search View Model.
 */

public class ProfileSearchState {
    private String username = "";
    private String errorMessage;

    public String getUsername() {
        return username;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
