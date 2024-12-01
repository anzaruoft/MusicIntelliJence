package interface_adapter.leave_rating;

import use_case.leave_rating.LeaveRatingInputBoundary;
import use_case.leave_rating.LeaveRatingInputData;
import view.LeaveRatingView;

public class LeaveRatingController {
    private final LeaveRatingInputBoundary leaveRatingInteractor;

    public LeaveRatingController(LeaveRatingInputBoundary leaveRatingInteractor) {
        this.leaveRatingInteractor = leaveRatingInteractor;
    }

    /**
     * Executes the Leave Rating Use Case.
     */
    public void execute(String username, String songTitle, String rating) {
        final LeaveRatingInputData leaveRatingInputData = new LeaveRatingInputData(username, songTitle, rating);

        leaveRatingInteractor.execute(leaveRatingInputData);
    }

    public void switchToLeaveRatingView(String songTitle) {
        leaveRatingInteractor.setSongTitle(songTitle);
    }

    public void switchToSongSearchView() {
        leaveRatingInteractor.switchToSongSearchView();
    }

    public void switchtoFeedView() {
        leaveRatingInteractor.switchtoFeedView();
    }

}

