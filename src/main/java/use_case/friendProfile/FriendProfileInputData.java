package use_case.friendProfile;

/**
 *
 */
public class FriendProfileInputData {
    private final String name;

    public FriendProfileInputData(String name) {
        this.name = name;
    }

    public String getUsername() {
        return name;
    }

}
