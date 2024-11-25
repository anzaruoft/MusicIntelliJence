package use_case.leave_rating;

import use_case.song_search.SongSearchInputData;
import use_case.song_search.SongSearchOutputBoundary;
import use_case.song_search.SongSearchUserDataAccessInterface;

public class LeaveRatingInteractor implements LeaveRatingInputBoundary {
    private final LeaveRatingUserDataAccessInterface userDataAccessObject;
    private final LeaveRatingOutputBoundary userPresenter;

    public LeaveRatingInteractor(LeaveRatingUserDataAccessInterface leaveRatingUserDataAccessInterface,
                                LeaveRatingOutputBoundary leaveRatingOutputBoundary) {
        this.userDataAccessObject = leaveRatingUserDataAccessInterface;
        this.userPresenter = leaveRatingOutputBoundary;
    }
    @Override
    public void execute(LeaveRatingInputData leaveRatingInputData) {
    }

    // COULD BE SOMETHING ELSE
    @Override
    public void switchToFeedView() {
    }
}
