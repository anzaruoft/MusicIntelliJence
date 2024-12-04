package use_case.friends;

import org.json.JSONArray;

/**
 * Output Data for the Friends Use Case.
 */
public class FriendsOutputData {

    private final JSONArray friendsList;

    public FriendsOutputData(JSONArray friendsList) {
        this.friendsList = friendsList;
    }

    public JSONArray getFriendsNames() {
        return friendsList;
    }
}
