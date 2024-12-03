package entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserTest {

    private CommonUser user;

    @BeforeEach
    void setUp() {
        user = new CommonUser("Michael", "MichaelPhelps3", "MichaelPhelps@gmail.com");
    }

    @Test
    void getName() {
        assertEquals("Michael", user.getName());
    }

    @Test
    void getPassword() {
        assertEquals("MichaelPhelps3", user.getPassword());
    }

    @Test
    void getEmail() {
        assertEquals("MichaelPhelps@gmail.com", user.getEmail());
    }

    @Test
    void getPosts() {
        assertEquals(0, user.getPosts().length());
    }

    @Test
    void getTopSongs() {
        assertEquals(0, user.getTopSongs().length());
    }

    @Test
    void getFriends() {
        assertEquals(0, user.getFriends().length());
    }

    @Test
    void setFriends() {
        JSONArray names = new JSONArray();
        names.put("Freeda");
        names.put("Yolanda");
        user.setFriends(names);
        assertEquals(2, user.getFriends().length());
    }

    @Test
    void setPosts() throws JsonProcessingException {
        JSONArray posts = new JSONArray();
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> postOne = new HashMap<>();
        postOne.put("postID", 1);
        postOne.put("title", "Christmas music is the worst music.");
        postOne.put("description", "All of the music is so repetitive, I just do not like it!");
        postOne.put("date", "2024-11-22");
        postOne.put("likes", 8);
        String jsonOne = objectMapper.writeValueAsString(postOne);

        Map<String, Object> postTwo = new HashMap<>();
        postTwo.put("postID", 1);
        postTwo.put("title", "Drake is awesome.");
        postTwo.put("description", "Every song is a banger, what can I say, absolute legend.");
        postTwo.put("date", "2024-11-23");
        postTwo.put("likes", 2);
        String jsonTwo = objectMapper.writeValueAsString(postTwo);

        Map<String, Object> postThree = new HashMap<>();
        postThree.put("postID", 1);
        postThree.put("title", "Prince Throwback.");
        postThree.put("description", "I have been so into Prince recently, so nice for the blast from the past!");
        postThree.put("date", "2024-11-24");
        postThree.put("likes", 4);
        String jsonThree = objectMapper.writeValueAsString(postThree);
        posts.put(jsonOne);
        posts.put(jsonTwo);
        posts.put(jsonThree);
        user.setPosts(posts);

        assertEquals(3, user.getPosts().length());
    }

    @Test
    void setTopSongs() {
        JSONArray topSongs = new JSONArray();
        topSongs.put("Let it go");
        topSongs.put("Merry Christmas");
        topSongs.put("Happy Birthday");
        user.setTopSongs(topSongs);
        assertEquals(3, user.getTopSongs().length());
    }
}