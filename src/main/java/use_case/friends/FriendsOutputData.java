package use_case.friends;

import java.util.List;

/**
 * Output Data for the Friends Use Case.
 */
public class FriendsOutputData {

    private final List<String> friendsList;

    public FriendsOutputData(List<String> friendsList) {
        this.friendsList = friendsList;
    }

    public List<String> getFriendsNames() {
        return friendsList;
    }
}
