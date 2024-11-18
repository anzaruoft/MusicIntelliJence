package use_case.friends;

/**
 * The Input Data for the Friends Use Case.
 */
public class FriendsInputData {

    private final String username;

    public FriendsInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
