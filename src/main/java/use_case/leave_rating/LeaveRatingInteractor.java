package use_case.leave_rating;

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

    @Override
    public void switchToSongSearchView() {
        userPresenter.switchToSongSearchView();
    }
}
