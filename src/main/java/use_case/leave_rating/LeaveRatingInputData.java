package use_case.leave_rating;

/**
 * This is the LeaveRatingInputData class.
 */
public class LeaveRatingInputData {
    private String username;
    private String songTitle;
    private String rating;

    public LeaveRatingInputData(String username, String songTitle, String rating) {
        this.username = username;
        this.songTitle = songTitle;
        this.rating = rating;
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

    public String getRating() {
        return rating;
    }
}
