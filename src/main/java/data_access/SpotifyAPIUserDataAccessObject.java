package data_access;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SpotifyAPIUserDataAccessObject {

    // Your Spotify credentials
    private static final String CLIENT_ID = "62b03d272d244fa192183e14436adc3c";
    private static final String CLIENT_SECRET = "d805ecb1bc474bec88c13b8cecb25abe";

    // Function to get an access token
    private static String getAccessToken() throws Exception {
        String authUrl = "https://accounts.spotify.com/api/token";
        String credentials = CLIENT_ID + ":" + CLIENT_SECRET;
        String encodedCredentials = java.util.Base64.getEncoder().encodeToString(credentials.getBytes());

        URL url = new URL(authUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        // Send POST data
        String data = "grant_type=client_credentials";
        try (OutputStream os = connection.getOutputStream()) {
            os.write(data.getBytes());
        }

        // Get the response
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        return jsonResponse.getString("access_token");
    }

    public static String getSongTitle(String jsonResponse) throws Exception {
        // Parse the JSON response
        JSONObject jsonObject = new JSONObject(jsonResponse);

        // Navigate to the tracks object
        JSONObject tracks = jsonObject.getJSONObject("tracks");

        // Get the items array
        JSONArray items = tracks.getJSONArray("items");

        if (items.length() > 0) {
            // Extract the first item's name (song title)
            JSONObject firstItem = items.getJSONObject(0);
            String songTitle = firstItem.getString("name");
            return songTitle;
        } else {
            throw new Exception("No songs found in the response.");
        }
    }

//     Function to search for a song
    public static String searchSong(String query) throws Exception {
        String accessToken = getAccessToken(); // Ensure this works and returns a valid token.

        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        String searchUrl = "https://api.spotify.com/v1/search?q=" + encodedQuery + "&type=track&limit=1";

        URL url = new URL(searchUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Extract the song title
            return getSongTitle(response.toString());
        } else {
            throw new Exception("Error: HTTP " + responseCode);
        }
    }
}