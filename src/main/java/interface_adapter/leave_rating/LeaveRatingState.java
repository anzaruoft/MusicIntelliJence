package interface_adapter.leave_rating;

public class LeaveRatingState {
    private String songTitle = "";

    public LeaveRatingState(String username) {
        this.songTitle = username;
    }

    public LeaveRatingState() {
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }
}
