package interface_adapter.feed;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import use_case.feed.FeedOutputBoundary;
import use_case.feed.FeedOutputData;

import javax.swing.text.View;

public class FeedPresenter implements FeedOutputBoundary {

    private FeedViewModel feedViewModel;
    private ViewManagerModel viewManagerModel;
    private ProfileViewModel profileViewModel;

    public FeedPresenter(ViewManagerModel viewManagerModel, FeedViewModel feedViewModel,
                         ProfileViewModel profileViewModel) {
        this.feedViewModel = feedViewModel;
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
    }

    @Override
    public void prepareSuccessView(FeedOutputData outputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    @Override
    public void switchToProfileView(String username) {
        final ProfileState profileState = profileViewModel.getState();
        // feedState.setUsername(response.getUsername());
        this.profileViewModel.setState(profileState);
        this.profileViewModel.firePropertyChanged();

        this.viewManagerModel.setState(profileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
