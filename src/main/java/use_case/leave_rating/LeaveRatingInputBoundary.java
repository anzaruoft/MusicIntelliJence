package use_case.leave_rating;

/**
 * This is the LeaveRatingInputBoundary interface.
 */
public interface LeaveRatingInputBoundary {
    /**
     * Execute the Leave Rating Use Case.
     *
     * @param leaveRatingInputData the input data for this use case
     */
    void execute(LeaveRatingInputData leaveRatingInputData);

    /**
     * This is the switchToSongSearchView function.
     */
    void switchToSongSearchView();

    /**
     * This is the switchToFeedView function.
     */
    void switchtoFeedView();
}
