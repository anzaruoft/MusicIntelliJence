package use_case.leave_rating;

public interface LeaveRatingInputBoundary {
    /**
     * Execute the Leave Rating Use Case.
     *
     * @param leaveRatingInputData the input data for this use case
     */
    void execute(LeaveRatingInputData leaveRatingInputData);

    // COULD BE SOMETHING ELSE HERE
    void switchToSongSearchView();

    void switchtoFeedView();
}
