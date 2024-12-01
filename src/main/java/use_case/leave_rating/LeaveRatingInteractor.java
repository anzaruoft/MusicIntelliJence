package use_case.leave_rating;

public class LeaveRatingInteractor implements LeaveRatingInputBoundary {
    private final LeaveRatingUserDataAccessInterface userDataAccessObject;
    private final LeaveRatingOutputBoundary userPresenter;
    private String songTitle;

    public LeaveRatingInteractor(LeaveRatingUserDataAccessInterface leaveRatingUserDataAccessInterface,
                                 LeaveRatingOutputBoundary leaveRatingOutputBoundary) {
        this.userDataAccessObject = leaveRatingUserDataAccessInterface;
        this.userPresenter = leaveRatingOutputBoundary;
    }

    @Override
    public void execute(LeaveRatingInputData leaveRatingInputData) {
    }

    @Override
    public void switchToSongSearchView() {
        userPresenter.switchToSongSearchView();
    }

    @Override
    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle; // Store the song title
    }
}