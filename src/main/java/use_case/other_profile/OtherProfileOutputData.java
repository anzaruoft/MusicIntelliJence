package use_case.other_profile;

/**
 * Output Data for the Other Profile Use Case.
 */
public class OtherProfileOutputData {
    private final String otherUsername;
    private final int friendsCount;

    public OtherProfileOutputData(String otherUsername,
                                  int friendsCount) {
        this.otherUsername = otherUsername;
        this.friendsCount = friendsCount;
    }

    public String getOtherUsername() {
        return otherUsername;
    }

    public int getFriendsCount() {
        return friendsCount;
    }
}
