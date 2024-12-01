package interface_adapter.song_search;

public class SongSearchState {
    private String songTitle = "";

    public SongSearchState(String songTitle) {
        this.songTitle = songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongTitle() {
        return songTitle;
    }
}

