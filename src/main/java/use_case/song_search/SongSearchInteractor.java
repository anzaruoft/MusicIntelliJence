package use_case.song_search;

/**
 * This is the SongSearchInteractor class.
 */
public class SongSearchInteractor implements SongSearchInputBoundary {
    private final SongSearchUserDataAccessInterface userDataAccessObject;
    private final SongSearchOutputBoundary userPresenter;

    public SongSearchInteractor(SongSearchUserDataAccessInterface songSearchDataAccessInterface,
                                SongSearchOutputBoundary songSearchOutputBoundary) {
        this.userDataAccessObject = songSearchDataAccessInterface;
        this.userPresenter = songSearchOutputBoundary;
    }

    @Override
    public void execute(SongSearchInputData songSearchInputData) {
    }

    @Override
    public void switchToFeedView() {
        userPresenter.switchToFeedView();
    }

    @Override
    public void switchToLeaveRatingView() {
        userPresenter.switchToLeaveRatingView();
    }
}
