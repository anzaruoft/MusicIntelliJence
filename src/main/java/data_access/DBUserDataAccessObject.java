package data_access;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import entity.User;
import entity.UserFactory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface {

    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String JSON_FILE_URL = "http://ec2-3-139-82-243.us-east-2.compute.amazonaws.com/example_input.json";
    private final UserFactory userFactory;

    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    // Helper method to retrieve the entire JSON file
    private JSONObject getJsonData() throws IOException, JSONException {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(JSON_FILE_URL)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        final Response response = client.newCall(request).execute();

        if (response.code() != SUCCESS_CODE) {
            throw new RuntimeException("Unexpected HTTP status code: " + response.code());
        }
        return new JSONObject(response.body().string());
    }

    @Override
    public User get(String username) {
        try {
            final JSONObject responseBody = getJsonData();
            final JSONArray usersArray = responseBody.getJSONArray("users");

            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJSONObject = usersArray.getJSONObject(i);
                if (userJSONObject.getString("username").equals(username)) {
                    String name = userJSONObject.getString("username");
                    String password = userJSONObject.getString("password");
                    String email = userJSONObject.getString("email");

                    return userFactory.create(name, password, email);
                }
            }
            throw new RuntimeException("User not found");
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Error fetching user data: " + ex.getMessage(), ex);
        }
    }

    @Override
    public boolean existsByName(String username) {
        try {
            final JSONObject responseBody = getJsonData();
            final JSONArray usersArray = responseBody.getJSONArray("users");

            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJSONObject = usersArray.getJSONObject(i);
                if (userJSONObject.getString("username").equals(username)) {
                    return true; // User exists
                }
            }
            return false; // User not found
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Error checking if user exists: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void save(User user) {
        try {
            final JSONObject responseBody = getJsonData();
            final JSONArray usersArray = responseBody.getJSONArray("users");

            // Create the new user JSONObject
            JSONObject newUser = new JSONObject();
            newUser.put("username", user.getName());
            newUser.put("password", user.getPassword());
            newUser.put("email", user.getEmail());

// Initialize empty arrays for followers, following, posts, and ratings
            newUser.put("followers", new JSONArray());
            newUser.put("following", new JSONArray());
            newUser.put("posts", new JSONArray());
            newUser.put("ratings", new JSONArray());

// Wrap the new user inside the "users" array
            usersArray.put(newUser);

// Create the final JSON object with the "users" key
            JSONObject finalJsonData = new JSONObject();
            finalJsonData.put("users", usersArray);

// Send the final JSON data to the server
            sendJsonData(finalJsonData);

        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException("Error saving user: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void changePassword(User user) {
        try {
            final JSONObject responseBody = getJsonData();
            final JSONArray usersArray = responseBody.getJSONArray("users");

            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject userJSONObject = usersArray.getJSONObject(i);
                if (userJSONObject.getString("username").equals(user.getName())) {
                    userJSONObject.put("password", user.getPassword()); // Update the password

                    // Send the updated JSON back to the server
                    sendJsonData(responseBody);
                    return; // Exit after updating
                }
            }
            throw new RuntimeException("User not found");
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Error changing password: " + ex.getMessage(), ex);
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
        return null; // Not implemented
    }
}
