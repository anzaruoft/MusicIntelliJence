package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.User;
import org.json.JSONArray;
import org.json.JSONException;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.feed.FeedUserDataAccessInterface;
import use_case.friendProfile.FriendProfileUserDataAccessInterface;
import use_case.friends.FriendsUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.other_profile.OtherProfileUserDataAccessInterface;
import use_case.profile.ProfileUserDataAccessInterface;
import use_case.profile_search.ProfileSearchUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        FeedUserDataAccessInterface,
        ProfileUserDataAccessInterface,
        FriendsUserDataAccessInterface,
        FriendProfileUserDataAccessInterface,
        ProfileSearchUserDataAccessInterface,
        OtherProfileUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    private String currentUser;
    private String searchedUser;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    /**
     * This is the getUser method.
     * @param inputUsername is a String.
     * @return a User.
     */
    public User getUser(String inputUsername) {
        return users.get(inputUsername);
    }

    @Override
    public String getCurrentUsername() {
        return currentUser;
    }

    @Override
    public void setCurrentUsername(String username) {
        currentUser = username;
    }

    @Override
    public void setSearchedUsername(String username) {
        searchedUser = username;
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
    }

    public JSONArray getFriendsPosts(JSONArray friends) {
        final JSONArray allPosts = new JSONArray();
        for (int i = 0; i < friends.length(); i++) {
            try {
                // Assuming `friends` contains JSONObjects with user keys
                String friendKey = friends.getString(i); // Get the key for the friend
                final User friendUser = users.get(friendKey); // Retrieve the User object

                if (friendUser != null) {
                    // Get the posts and add them to the result
                    allPosts.putAll(friendUser.getPosts());
                }
            } catch (JSONException e) {
                // Handle potential parsing issues
                e.printStackTrace();
            }
        }

        return allPosts;
    }

    @Override
    public void updateUserPosts(User user) {
    }
}