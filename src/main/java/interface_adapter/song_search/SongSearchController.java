package interface_adapter.song_search;

import use_case.song_search.SongSearchInputBoundary;
import use_case.song_search.SongSearchInputData;

/**
 * This is the SongSearchController class.
 */
public class SongSearchController {
    private final SongSearchInputBoundary songSearchInteractor;

    public SongSearchController(SongSearchInputBoundary songSearchInteractor) {
        this.songSearchInteractor = songSearchInteractor;
    }

    /**
     * Executes the Song Search Use Case.
     */
    public void execute() {
        final SongSearchInputData songSearchInputData = new SongSearchInputData();
        songSearchInteractor.execute(songSearchInputData);
    }

    /**
     * This is the switchToFeedView function.
     */
    public void switchToFeedView() {
        songSearchInteractor.switchToFeedView();
    }

    /**
     * This is the switchToLeaveRatingView function.
     */
    public void switchToLeaveRatingView() {
        songSearchInteractor.switchToLeaveRatingView();
    }
}
