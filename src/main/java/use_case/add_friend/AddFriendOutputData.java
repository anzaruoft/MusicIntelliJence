package use_case.add_friend;

/**
 * Output data for the Add Friend Use Case.
 */
public class AddFriendOutputData {

    private final String friendName;

    private final String username;

    private final boolean useCaseFailed;

    public AddFriendOutputData(String friendName, String username, boolean useCaseFailed) {
        this.friendName = friendName;
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getFriendName() {
        return friendName;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
