package data_access;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
import use_case.profile.ProfileUserDataAccessInterface;
import use_case.profile_search.ProfileSearchUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.song_search.SongSearchUserDataAccessInterface;

public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        FeedUserDataAccessInterface,
        ProfileUserDataAccessInterface,
        FriendsUserDataAccessInterface,
        FriendProfileUserDataAccessInterface,
        ProfileSearchUserDataAccessInterface{
        LeaveRatingUserDataAccessInterface,
        SongSearchUserDataAccessInterface {

    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String JSON_FILE_URL = "http://ec2-3-139-82-243.us-east-2.compute.amazonaws.com:8080/users";
    private final UserFactory userFactory;

    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @Override
    public User get(String username) {
        final String endpoint2 = "/user-information";
        String url = JSON_FILE_URL + endpoint2 + "?username=" + username;
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();

                // Log the raw response body
                System.out.println("Response body: " + responseBody);

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
                    System.out.println(jsonObject.get("sentFriends").getClass());
                    user.setSentFriends((JSONArray) jsonObject.get("sentFriends"));
                    user.setReceivedFriends((JSONArray) jsonObject.get("receivedFriends"));
                    user.setFriends((JSONArray) jsonObject.get("friends"));
                    user.setPosts((JSONArray) jsonObject.get("posts"));
                    user.setRatings((JSONArray) jsonObject.get("ratings"));
                    return user;
                } catch (JSONException e) {
                    System.err.println("Failed to parse JSON: " + e.getMessage());
                    throw e; // Rethrow exception if JSON parsing fails
                }
            } else {
                System.err.println("Request failed. Response code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (JSONException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
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
            OkHttpClient client = new OkHttpClient();
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    String jsonResponse = response.body().string();
                    System.out.println("Response: " + response);
                    System.out.println("Response body: " + jsonResponse);
                    JSONObject jsonObject = new JSONObject(jsonResponse);
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
        catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
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
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changePassword(User user) {
        try {
            save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public String getPassword(String username) {
//        final User commonUser = get(username);
//        return commonUser.getSentFriends();
//    }
//
//    @Override
//    public String getSentFriends(String username) {
//        final User commonUser = get(username);
//        return commonUser.getSentFriends();
//    }
//
//    @Override
//    public String getRatings(String username) {
//        final User commonUser = get(username);
//        return commonUser.getSentFriends();
//    }
//
//    @Override
//    public String getReceivedFriends(String username) {
//        final User commonUser = get(username);
//        return commonUser.getSentFriends();
//    }
//
//    @Override
//    public String getPosts(String username) {
//        final User commonUser = get(username);
//        return commonUser.getSentFriends();
//    }
//
//    @Override
//    public String getEmail(String username) {
//        final User commonUser = get(username);
//        return commonUser.getSentFriends();
//    }
//
//    @Override
//    public String getFriends(String username) {
//        final User commonUser = get(username);
//        return commonUser.getFriends();
//    }


//    @Override
//    public void addPost(User user) {
//        try {
//            save(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public boolean addPost(User user, String newPost) {
//        try {
//            final String endpoint = "/updated-user";
//            final URI uri = new URI(JSON_FILE_URL + endpoint);
//            final RequestBody formBody = new FormBody.Builder()
//                    .add("username", user.getName())
//                    .add("password", user.getPassword())
//                    .add("sentFriends", user.getSentFriends())
//                    .add("ratings", user.getRatings())
//                    .add("receivedFriends", user.getReceivedFriends())
//                    .add("posts", user.getPosts())
//                    .add("email", user.getEmail())
//                    .add("friends", user.getFriends())
//                    .build();
//            final Request request = new Request.Builder()
//                    .url(uri.toURL())
//                    .get()
//                    .put()
//                    .build();
//            OkHttpClient client = new OkHttpClient();
//            try (Response response = client.newCall(request).execute()) {
//                if (response.isSuccessful()) {
//                    System.out.println("User updated successfully: " + response.body().string());
//                }
//                else {
//                    System.out.println("Failed to update user. Response code: " + response.code());
//                    System.out.println("Response body: " + response.body().string());
//                }
//            }
//            return false;
//        }
//        catch (IOException | URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void addRating(User user) {
//        // Not sure if yall want a separate function for this or not?
//    }
//
//    @Override
//    public void addFriendRequest(User user) {
//        // Benny i'll leave this for you so it fits your work flow
//    }

    @Override
    public void setCurrentUsername(String name) {
        // Not implemented
    }

    @Override
    public String getCurrentUsername() {
        return null; // Not implemented
    }

}
