package interface_adapter.leave_rating;

public class LeaveRatingState {
    private String username = "";
    private String songTitle = "";

    public LeaveRatingState(String username) {
        this.username = username;
        this.songTitle = "";
    }

    public LeaveRatingState() {
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
}
