package use_case.leave_rating;

public interface LeaveRatingOutputBoundary {
    /**
     * Prepares the success view for the Leave Rating Use Case.
     *
     * @param outputData the output data
     */
    void prepareSuccessView(LeaveRatingOutputData outputData);

    /**
     * Prepares the failure view for the Leave Rating Use Case.
     *
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    void switchToSongSearchView();

    void switchToFeedView();
}
