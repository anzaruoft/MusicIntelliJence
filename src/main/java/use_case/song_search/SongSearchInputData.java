package use_case.song_search;

/**
 * The input data for the song search use case.
 */
public class SongSearchInputData {

    private final String songName;

    // Constructor is already fine; it's setting the songName
    public SongSearchInputData(String songName) {
        this.songName = songName;
    }

    // Public getter for accessing the song name
    public String getSongName() {
        return songName;
    }
}
