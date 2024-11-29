package use_case.song_search;

import use_case.song_search.SongSearchInputData;

public interface SongSearchInputBoundary {
    /**
     * Execute the Song Search Use Case.
     * @param songSearchInputData the input data for this use case
     */
    void execute(SongSearchInputData songSearchInputData);

    void switchToFeedView();

    void switchToLeaveRatingView();
}
