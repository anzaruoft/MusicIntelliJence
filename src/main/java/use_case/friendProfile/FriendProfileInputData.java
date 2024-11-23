package use_case.friendProfile;

/**
 * The Input Data for the Friend Profile Use Case.
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
