package use_case.profile_search;

/**
 * Output Data for the Profile Search Use Case.
 */

// Implement once Use case Interactor / DAI /  is finished

public class ProfileSearchOutputData {

    private final int friendCount;
    private final String searchedUserName;
    private final String thisUserName;

    public ProfileSearchOutputData(int friendCount, String searchedUserName, String thisUserName) {
        this.friendCount = friendCount;
        this.searchedUserName = searchedUserName;
        this.thisUserName = thisUserName;
    }

    public int getFriendCount() {
        return friendCount;
    }

    public String getSearchedUserName() {
        return searchedUserName;
    }

    public String getThisUserName() {
        return thisUserName;
    }
}
