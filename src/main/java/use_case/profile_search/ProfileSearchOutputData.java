package use_case.profile_search;

/**
 * Output Data for the Profile Search Use Case.
 */

// Implement once Use case Interactor / DAI /  is finished

public class ProfileSearchOutputData {

    private final int friendCount;
    private final String searchedUserName;

    public ProfileSearchOutputData(int friendCount, String searchedUserName) {
        this.friendCount = friendCount;
        this.searchedUserName = searchedUserName;
    }

    public int getFriendCount() {
        return friendCount;
    }

    public String getSearchedUserName() {
        return searchedUserName;
    }
}
