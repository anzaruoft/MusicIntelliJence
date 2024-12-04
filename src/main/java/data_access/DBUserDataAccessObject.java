package data_access;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import entity.CommonUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import entity.User;
import entity.UserFactory;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.feed.FeedUserDataAccessInterface;
import use_case.friendProfile.FriendProfileUserDataAccessInterface;
import use_case.friends.FriendsUserDataAccessInterface;
import use_case.leave_rating.LeaveRatingUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.other_profile.OtherProfileUserDataAccessInterface;
import use_case.profile.ProfileUserDataAccessInterface;
import use_case.profile_search.ProfileSearchUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.song_search.SongSearchUserDataAccessInterface;

/**
 * DAO for user data implemented using a database to persist the data.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        FeedUserDataAccessInterface,
        ProfileUserDataAccessInterface,
        FriendsUserDataAccessInterface,
        FriendProfileUserDataAccessInterface,
        ProfileSearchUserDataAccessInterface,
        LeaveRatingUserDataAccessInterface,
        SongSearchUserDataAccessInterface,
        OtherProfileUserDataAccessInterface {

    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String JSON_FILE_URL = "http://ec2-3-139-82-243.us-east-2.compute.amazonaws.com:8080/users";
    private final UserFactory userFactory;
    private String currentUser;
    private String searchedUser;

    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @Override
    public User get(String username) {
        final String endpoint2 = "/user-information";
        final String url = JSON_FILE_URL + endpoint2 + "?username=" + username;
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        final OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                final String responseBody = response.body().string();

                // Log the raw response body
//                System.out.println("Response body: " + responseBody);

                // Check if the response indicates an error message like "User not found"
                if (responseBody.equalsIgnoreCase("User not found")) {
                    System.out.println("User not found for username: " + username);
                    return null; // Return null if the user is not found
                }

                // Check if the response body starts with '{' (valid JSON object)
                if (!responseBody.trim().startsWith("{")) {
                    throw new JSONException("Response body is not a valid JSON object");
                }

                // Attempt to parse the response into a JSONObject
                try {
                    final JSONObject jsonObject = new JSONObject(responseBody);
                    System.out.println(jsonObject);
                    // Maybe need to set the other information
                    final CommonUser user = new CommonUser(
                            username,
                            jsonObject.getString("password"),
                            jsonObject.getString("email")
                    );
//                    System.out.println(jsonObject.get("sentFriends").getClass());
                    user.setSentFriends((JSONArray) jsonObject.get("sentFriends"));
                    user.setReceivedFriends((JSONArray) jsonObject.get("receivedFriends"));
                    user.setFriends((JSONArray) jsonObject.get("friends"));
                    user.setPosts((JSONArray) jsonObject.get("posts"));
                    user.setRatings((JSONArray) jsonObject.get("ratings"));
                    return user;
                }
                catch (JSONException evt) {
                    System.err.println("Failed to parse JSON: " + evt.getMessage());
                    throw evt; // Rethrow exception if JSON parsing fails
                }
            }
            else {
                System.err.println("Request failed. Response code: " + response.code());
            }
        }
        catch (IOException evt) {
            evt.printStackTrace();
            throw new RuntimeException(evt);
        }
        catch (JSONException evt) {
            evt.printStackTrace();
            throw evt;
        }
        return null;
    }

    @Override
    public JSONArray getFriendsPosts(JSONArray friends) {
        return friends;
    }

    @Override
    public boolean existsByName(String username) {
        try {
            final String endpoint = "/json-information";
            final URI uri = new URI(JSON_FILE_URL + endpoint);
            final Request request = new Request.Builder()
                    .url(uri.toURL())
                    .get()
                    .build();
            final OkHttpClient client = new OkHttpClient();
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    final String jsonResponse = response.body().string();
                    System.out.println("Response: " + response);
                    System.out.println("Response body: " + jsonResponse);
                    System.out.println("THIS IS THE JSON RESPONSEEEEEE: " + jsonResponse);
                    final JSONObject jsonObject = new JSONObject(jsonResponse);
                    for (String key : jsonObject.keySet()) {
                        if (key.equals(username)) {
                            return true;
                        }
                    }
                    System.out.println("User not found for username: " + username);
                }
            }
            return false;
        }
        catch (IOException | URISyntaxException evt) {
            throw new RuntimeException(evt);
        }
    }

    // ADDED
    @Override
    public boolean isFriends(String thisUsername, String otherUsername) {
        try {
            final String endpoint = "/json-information";
            final URI uri = new URI(JSON_FILE_URL + endpoint);
            final Request request = new Request.Builder()
                    .url(uri.toURL())
                    .get()
                    .build();
            final OkHttpClient client = new OkHttpClient();
            try (Response response = client.newCall(request).execute()) {
                System.out.println("past first part");
                if (response.isSuccessful()) {
                    System.out.println("if section 1");
                    final String jsonResponse = response.body().string();
                    System.out.println("Response: " + response);
                    System.out.println("Response body: " + jsonResponse);

                    final JSONObject jsonObject = new JSONObject(jsonResponse);

                    // Check if the main JSON object contains the username (thisUsername)
                    if (jsonObject.has(thisUsername)) {
                        System.out.println("if section 2");
                        JSONObject userJson = jsonObject.getJSONObject(thisUsername);

                        // Ensure the 'friends' array exists for the user
                        if (userJson.has("friends")) {
                            System.out.println("if section 3");
                            JSONArray friendsArray = userJson.getJSONArray("friends");

                            // Check if otherUsername is in the friends array
                            for (int i = 0; i < friendsArray.length(); i++) {
                                System.out.println("for section");
                                if (friendsArray.getString(i).equals(otherUsername)) {
                                    System.out.println("if section 4");
                                    return true;  // They are friends
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("try call failed");
            return false;
        }
        catch (IOException | URISyntaxException evt) {
            throw new RuntimeException(evt);
        }
    }

    @Override
    public void save(User user) {
        try {
            final String endpointAdd = "/add";
            final URI uri = new URI(JSON_FILE_URL + endpointAdd);
            final RequestBody formBody = new FormBody.Builder()
                    .add("username", user.getName())
                    .add("password", user.getPassword())
                    .add("email", user.getEmail())
                    .build();

            final Request request = new Request.Builder()
                    .url(uri.toURL())
                    .addHeader(CONTENT_TYPE_LABEL, "application/x-www-form-urlencoded")
                    .post(formBody)
                    .build();
            OkHttpClient client = new OkHttpClient();
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    System.out.println("User added successfully: " + response.body().string());
                }
                else {
                    System.out.println("Failed to add user. Response code: " + response.code());
                    System.out.println("Response body: " + response.body().string());
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception evt) {
            evt.printStackTrace();
        }
    }

    @Override
    public void updateUserFriends(User thisUser, User otherUser) {
        try {
            JSONArray friends = thisUser.getFriends();
            if (friends == null) {
                friends = new JSONArray();
                System.out.println("new JSON array created in update user friends");
            }
            thisUser.setFriends(friends);
            System.out.println("This is the current user who is logged in: " + thisUser.getName());
            System.out.println("This is the current user's friends: " + thisUser.getFriends());

            final String endpoint = "/updated-user";
            final URI uri = new URI(JSON_FILE_URL + endpoint);
            final RequestBody formBody = new FormBody.Builder()
                .add("username", thisUser.getName())
                .add("password", thisUser.getPassword())
                .add("sentFriends", thisUser.getSentFriends().toString())
                .add("ratings", thisUser.getRatings().toString())
                .add("receivedFriends", thisUser.getReceivedFriends().toString())
                .add("posts", thisUser.getPosts().toString())
                .add("email", thisUser.getEmail())
                .add("friends", friends.toString()) // Use the friends JSONArray directly
                .build();
            System.out.println("LOOK BELOW");
            System.out.println(formBody);
            final Request request = new Request.Builder()
                .url(uri.toURL())
                .put(formBody)
                .build();
            OkHttpClient client = new OkHttpClient();
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    System.out.println("User updated successfully: " + response.body().string());
                } else {
                    System.out.println("Failed to update user. Response code: " + response.code());
                    System.out.println("Response body: " + response.body().string());
                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);

        }
        System.out.println("User updated successfully");
        System.out.println(thisUser.getFriends().toString());
    }

    /**
     * We use this in the updateUsersPost function.
     * @param inputUsername is type String.
     * @return a User.
     */
    public User getUser(String inputUsername) {
        return get(inputUsername);
    }

    @Override
    public void updateUserPosts(User user) {
        try {
            JSONArray posts = user.getPosts();
            if (posts == null) {
                posts = new JSONArray();
            }
            final User currentUser = getUser(user.getName());
            currentUser.setPosts(posts);
            System.out.println("This is the current user who is logged in: " + currentUser.getName());
            System.out.println("This is the current user's posts: " + currentUser.getPosts());

            // Do not append the stringified version
            final String endpoint = "/updated-user";
            final URI uri = new URI(JSON_FILE_URL + endpoint);
            final RequestBody formBody = new FormBody.Builder()
                    .add("username", user.getName())
                    .add("password", user.getPassword())
                    .add("sentFriends", user.getSentFriends().toString())
                    .add("ratings", user.getRatings().toString())
                    .add("receivedFriends", user.getReceivedFriends().toString())
                    .add("posts", posts.toString()) // Use the posts JSONArray directly
                    .add("email", user.getEmail())
                    .add("friends", user.getFriends().toString())
                    .build();
            System.out.println("LOOK BELOW");
            System.out.println(formBody);
            final Request request = new Request.Builder()
                    .url(uri.toURL())
                    .put(formBody)
                    .build();
            OkHttpClient client = new OkHttpClient();
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    System.out.println("User updated successfully: " + response.body().string());
                } else {
                    System.out.println("Failed to update user. Response code: " + response.code());
                    System.out.println("Response body: " + response.body().string());
                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User updated successfully");
        System.out.println(user.getPosts().toString());
    }

    @Override
    public void changePassword(User user) {
        try {
            save(user);
        }
        catch (Exception evt) {
            evt.printStackTrace();
        }
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUser = name;
    }

    @Override
    public String getCurrentUsername() {
        return currentUser;
    }

}
