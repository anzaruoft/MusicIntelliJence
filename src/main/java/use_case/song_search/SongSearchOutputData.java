package use_case.song_search;

public class SongSearchOutputData {
    private final String songTitle;

    public SongSearchOutputData(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongTitle() {
        return songTitle;
    }
}
