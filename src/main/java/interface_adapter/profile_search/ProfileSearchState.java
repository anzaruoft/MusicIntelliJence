package interface_adapter.profile_search;

/**
 * The State for the Profile Search View Model.
 */

public class ProfileSearchState {
    private String searchedUsername = "";
    private String thisUsername = "";
    private String errorMessage;

    public String getSearchedUsername() {
        return searchedUsername;
    }

    public String getThisUsername() {
        return thisUsername;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setSearchedUsername(String searchedUsername) {
        this.searchedUsername = searchedUsername;
    }

    public void setThisUsername(String thisUsername) {
        this.thisUsername = thisUsername;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
