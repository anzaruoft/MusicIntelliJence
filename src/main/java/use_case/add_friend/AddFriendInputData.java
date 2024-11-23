package use_case.add_friend;

/**
 * The Input Data for the Add Friend Use Case.
 */

public class AddFriendInputData {

    private final String friendUserName;
    private final String username;

    public AddFriendInputData(String friend_username, String username) {
        this.friendUserName = friend_username;
        this.username = username;

    }

    public String getUsername() {
        return username;
    }

    public String getFriendUserName() {
        return friendUserName;
    }
}
