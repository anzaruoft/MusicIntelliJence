package interface_adapter.leave_rating;

import interface_adapter.ViewManagerModel;
import use_case.leave_rating.LeaveRatingOutputBoundary;
import use_case.leave_rating.LeaveRatingOutputData;

public class LeaveRatingPresenter implements LeaveRatingOutputBoundary {

    private LeaveRatingViewModel leaveRatingViewModel;
    private ViewManagerModel viewManagerModel;

    public LeaveRatingPresenter(ViewManagerModel viewManagerModel, LeaveRatingViewModel leaveRatingViewModel) {
        this.leaveRatingViewModel = leaveRatingViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(LeaveRatingOutputData outputData) {
    }

    @Override
    public void prepareFailView(String errorMessage) {
    }
}

