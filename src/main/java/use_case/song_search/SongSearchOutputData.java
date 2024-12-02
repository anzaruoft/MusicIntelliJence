package use_case.song_search;

/**
 * This is the SongSearchOutputData class.
 */
public class SongSearchOutputData {
    private final String songTitle;

    public SongSearchOutputData(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongTitle() {
        return songTitle;
    }
}
