package data_access;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import entity.CommonUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import entity.User;
import entity.UserFactory;
import okhttp3.FormBody;
import okhttp3.MediaType;
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
                System.out.println("Response body: " + responseBody);
                JSONObject jsonObject = new JSONObject(responseBody);
                return new CommonUser(
                        username,
                        jsonObject.getString("password"),
                        jsonObject.getString("email")
                );
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
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
                    JSONArray jsonArray = new JSONArray(jsonResponse);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        if (jsonArray.getString(i).equals(username)) {
                            return true;
                        }
                    }
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
            final String postsString = new JSONArray(user.getPosts()).toString();
            System.out.println("Request Data:");
            System.out.println("Username: " + user.getName());
            System.out.println("Password: " + user.getPassword());
            System.out.println("Email: " + "mantle@example.com");
            System.out.println("Posts: " + postsString);
            final RequestBody formBody = new FormBody.Builder()
                    .add("username", user.getName())
                    .add("password", user.getPassword())
                    .add("email", user.getEmail())
                    .add("posts", postsString)
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

    // Helper method to send the updated JSON data back to the server
    private void sendJsonData(JSONObject updatedData) throws IOException {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final RequestBody body = RequestBody.create(updatedData.toString(), mediaType);

        final Request request = new Request.Builder()
                .url(JSON_FILE_URL) // Ensure this is the correct endpoint for updating data
                .method("PUT", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        final Response response = client.newCall(request).execute();
        if (response.code() != SUCCESS_CODE) {
            System.out.println("Response code: " + response.code());
            System.out.println("Response body: " + response.body().string());
            throw new RuntimeException("Failed to update JSON data on the server");
        }
    }

    @Override
    public void setCurrentUsername(String name) {
        // Not implemented
    }

    @Override
    public String getCurrentUsername() {
        return null;
        // Not implemented
    }

    @Override
    public List<String> getFriendsPosts(List<String> friends) {
        final List<String> allPosts = new ArrayList<>();
        for (String friend : friends) {
            final User friendUser = get(friend);
            if (friendUser != null) {
                allPosts.addAll(friendUser.getPosts());
            }
        }
        return allPosts;
    }

}