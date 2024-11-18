package data_access;

import use_case.song_search.SongSearchUserDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class SongSearchDataAccessObject implements SongSearchUserDataAccessInterface {

    private final List<String> songs;

    public SongSearchDataAccessObject() {
        // Initialize with some mock data
        songs = new ArrayList<>();
        songs.add("Bohemian Rhapsody");
        songs.add("Stairway to Heaven");
        songs.add("Hotel California");
        songs.add("Imagine");
        songs.add("Hey Jude");
        songs.add("Smells Like Teen Spirit");
        songs.add("Wonderwall");
        songs.add("Sweet Child O' Mine");
        songs.add("Shape of You");
        songs.add("Blinding Lights");
    }

    @Override
    public boolean existsByName(String songName) {
        // Search for the song in the list (case-insensitive)
        return songs.stream()
                .anyMatch(song -> song.toLowerCase().contains(songName.toLowerCase()));
    }

    @Override
    public String get(String songName) {
        // Return the song from the list (if found)
        return songs.stream()
                .filter(song -> song.toLowerCase().equals(songName.toLowerCase()))
                .findFirst()
                .orElse(null);  // Return null if not found
    }
}
