package use_case.song_search;


import entity.User;

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
