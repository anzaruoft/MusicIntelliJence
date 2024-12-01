package use_case.friends;

import org.json.JSONArray;

import java.util.List;

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
