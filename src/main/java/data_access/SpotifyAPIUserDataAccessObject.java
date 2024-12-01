package data_access;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * DAO for the Spotify Data.
 */
public class SpotifyAPIUserDataAccessObject {

    // Your Spotify credentials
    private static final String CLIENT_ID = "62b03d272d244fa192183e14436adc3c";
    private static final String CLIENT_SECRET = "d805ecb1bc474bec88c13b8cecb25abe";
    private static final int RESPONSE_CODE_LIMIT = 200;

    // Function to get an access token
    private static String getAccessToken() throws Exception {
        final String authUrl = "https://accounts.spotify.com/api/token";
        final String credentials = CLIENT_ID + ":" + CLIENT_SECRET;
        final String encodedCredentials = java.util.Base64.getEncoder().encodeToString(credentials.getBytes());
        // Watched YouTube video to figure this out.

        final URL url = new URL(authUrl);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        // Send POST data
        final String data = "grant_type=client_credentials";
        try (OutputStream os = connection.getOutputStream()) {
            os.write(data.getBytes());
        }

        // Get the response
        final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        final StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        final JSONObject jsonResponse = new JSONObject(response.toString());
        return jsonResponse.getString("access_token");
    }

    /**
     * Endpoint for getting the song title.
     * @param jsonResponse is a String that takes in the jsonResponse.
     * @return the song title as a String.
     * @throws Exception no songs found in the response.
     */
    public static String getSongTitle(String jsonResponse) throws Exception {
        // Parse the JSON response
        final JSONObject jsonObject = new JSONObject(jsonResponse);

        // Navigate to the tracks object
        final JSONObject tracks = jsonObject.getJSONObject("tracks");

        // Get the items array
        final JSONArray items = tracks.getJSONArray("items");

        if (items.length() > 0) {
            // Extract the first item's name (song title)
            final JSONObject firstItem = items.getJSONObject(0);
            final String songTitle = firstItem.getString("name");
            return songTitle;
        }
        else {
            throw new Exception("No songs found in the response.");
        }
    }

    /**
     * Function to search for a song, reaches out to endpoint.
     * @param query is a String that is the query song.
     * @return the song title as a String.
     * @throws Exception if there is an error with the query.
     */
    public static String searchSong(String query) throws Exception {
        final String accessToken = getAccessToken();

        final String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
        final String searchUrl = "https://api.spotify.com/v1/search?q=" + encodedQuery + "&type=track&limit=1";

        final URL url = new URL(searchUrl);
        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);

        final int responseCode = conn.getResponseCode();
        if (responseCode == RESPONSE_CODE_LIMIT) {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Extract the song title
            return getSongTitle(response.toString());
        }
        else {
            throw new Exception("Error: HTTP " + responseCode);
        }
    }
}
