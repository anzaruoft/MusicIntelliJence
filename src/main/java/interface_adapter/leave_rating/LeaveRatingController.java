package interface_adapter.leave_rating;

import use_case.leave_rating.LeaveRatingInputBoundary;
import use_case.leave_rating.LeaveRatingInputData;

public class LeaveRatingController {
    private final LeaveRatingInputBoundary leaveRatingInteractor;

    public LeaveRatingController(LeaveRatingInputBoundary leaveRatingInteractor) {
        this.leaveRatingInteractor = leaveRatingInteractor;
    }

    /**
     * Executes the Leave Rating Use Case.
     */
    public void execute() {
        final LeaveRatingInputData leaveRatingInputData = new LeaveRatingInputData();

        leaveRatingInteractor.execute(leaveRatingInputData);
    }

    // COME BACK TO THIS
    public void switchToFeedView() {
    }
}
