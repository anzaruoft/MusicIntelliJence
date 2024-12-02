package use_case.song_search;

/**
 * This is the SongSearchOutputBoundary interface.
 */
public interface SongSearchOutputBoundary {
    /**
     * Prepares the success view for the Song Search Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SongSearchOutputData outputData);

    /**
     * Prepares the failure view for the Song Search Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * This is the switchToFeedView function.
     */
    void switchToFeedView();

    /**
     * This is the switchToLeaveRatingView function.
     */
    void switchToLeaveRatingView();
}
