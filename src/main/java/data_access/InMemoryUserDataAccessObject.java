package data_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.User;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.feed.FeedUserDataAccessInterface;
import use_case.friendProfile.FriendProfileUserDataAccessInterface;
import use_case.friends.FriendsUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
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
        ProfileSearchUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    private String currentUser;

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

    @Override
    public String getCurrentUsername() {
        return currentUser;
    }

    @Override
    public void setCurrentUsername(String username) {
        currentUser = username;
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
    }

    public List<String> getFriendsPosts(List<String> friends) {
        final List<String> allPosts = new ArrayList<>();
        for (String friend : friends) {
            final User friendUser = users.get(friend);
            if (friendUser != null) {
                allPosts.addAll(friendUser.getPosts());
            }
        }
        return allPosts;
    }

}