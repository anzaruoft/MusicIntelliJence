package interface_adapter.song_search;

public class SongSearchState {
    private String username = "";

    public SongSearchState(String username) {
        this.username = username;
    }

    public SongSearchState() {

    }

    public String getUsername() {
        return username;
    }
}
