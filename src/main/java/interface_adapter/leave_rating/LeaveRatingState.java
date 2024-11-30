package interface_adapter.leave_rating;

public class LeaveRatingState {
    private String username = "";

    public LeaveRatingState(String username) {
        this.username = username;
    }

    public LeaveRatingState() {
    }

    public String getUsername() {
        return username;
    }
}
