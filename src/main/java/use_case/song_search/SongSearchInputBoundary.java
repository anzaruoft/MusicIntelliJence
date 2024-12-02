package use_case.song_search;

/**
 * This is the SongSearchInputBoundary interface.
 */
public interface SongSearchInputBoundary {
    /**
     * Execute the Song Search Use Case.
     * @param songSearchInputData the input data for this use case
     */
    void execute(SongSearchInputData songSearchInputData);

    /**
     * This is the switchToFeedView function.
     */
    void switchToFeedView();

    /**
     * This is switchToLeaveRatingView function.
     */
    void switchToLeaveRatingView();
}
