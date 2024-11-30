package interface_adapter.leave_rating;

import interface_adapter.ViewModel;

public class LeaveRatingViewModel extends ViewModel<LeaveRatingState> {
    public LeaveRatingViewModel() {
        super("leave rating");
        setState(new LeaveRatingState());
    }
}
