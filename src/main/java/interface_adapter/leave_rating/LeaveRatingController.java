package interface_adapter.leave_rating;

import use_case.leave_rating.LeaveRatingInputBoundary;
import use_case.leave_rating.LeaveRatingInputData;

/**
 * LeaveRatingController class.
 */
public class LeaveRatingController {
    private final LeaveRatingInputBoundary leaveRatingInteractor;

    public LeaveRatingController(LeaveRatingInputBoundary leaveRatingInteractor) {
        this.leaveRatingInteractor = leaveRatingInteractor;
    }

    /**
     * Executes the Leave Rating Use Case.
     * @param username is the username, it's a string.
     * @param songTitle is the song title, it's a string.
     * @param rating is the rating, it's a string.
     */
    public void execute(String username, String songTitle, String rating) {
        final LeaveRatingInputData leaveRatingInputData = new LeaveRatingInputData(username, songTitle, rating);

        leaveRatingInteractor.execute(leaveRatingInputData);
    }

    /**
     * This is the switchToSongSearchView function.
     */
    public void switchToSongSearchView() {
        leaveRatingInteractor.switchToSongSearchView();
    }

    /**
     * This is the switchToFeedView function.
     */
    public void switchtoFeedView() {
        leaveRatingInteractor.switchtoFeedView();
    }

}

