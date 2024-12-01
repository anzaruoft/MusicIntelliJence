package interface_adapter.song_search;

public class SongSearchState {
    private String username = "";
    private String songTitle = "";
    private String rating = "";

    public SongSearchState(String username) {
        this.username = username;
        this.songTitle = "";
        this.rating = "";
    }

    public SongSearchState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
