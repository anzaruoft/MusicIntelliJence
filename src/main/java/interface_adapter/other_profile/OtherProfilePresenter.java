package interface_adapter.other_profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.feed.FeedViewModel;
import use_case.other_profile.OtherProfileOutputBoundary;
import use_case.other_profile.OtherProfileOutputData;

/**
 * The Presenter for the Other Profile Use Case.
 */

public class OtherProfilePresenter implements OtherProfileOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final OtherProfileViewModel otherProfileViewModel;

    // for feed view model, potentially change to friends search bar****
    // So feed view may change as the view to return to may be friend search page*****

    private final FeedViewModel feedViewModel;

    public OtherProfilePresenter(ViewManagerModel viewManagerModel,
                                 OtherProfileViewModel otherProfileViewModel,
                                 FeedViewModel feedViewModel
    ) {
        this.viewManagerModel = viewManagerModel;
        this.otherProfileViewModel = otherProfileViewModel;
        this.feedViewModel = feedViewModel;
    }

    @Override
    public void prepareSuccessView(OtherProfileOutputData otherProfileOutputData) {
        final OtherProfileState otherProfileState = otherProfileViewModel.getState();
        otherProfileState.setFriends(otherProfileOutputData.getFriends());
        otherProfileViewModel.setState(otherProfileState);
        otherProfileViewModel.firePropertyChanged();
        viewManagerModel.setState(otherProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final OtherProfileState otherProfileState = otherProfileViewModel.getState();
        otherProfileState.setUsernameError(error);
        otherProfileViewModel.firePropertyChanged();
    }

    // This would also change if friend search page is the prerequiset to view other profile
    @Override
    public void switchToFeedView() {
        viewManagerModel.setState(feedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
