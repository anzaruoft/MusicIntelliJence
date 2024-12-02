package interface_adapter.profile_search;

import interface_adapter.ViewManagerModel;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.feed.FeedState;
import interface_adapter.login.LoginState;
import interface_adapter.other_profile.OtherProfileState;
import interface_adapter.other_profile.OtherProfileViewModel;

import use_case.profile.ProfileOutputBoundary;
import use_case.profile_search.ProfileSearchOutputBoundary;
import use_case.profile_search.ProfileSearchOutputData;


/**
 * The Presenter for the Profile Search Use Case.
 */

public class ProfileSearchPresenter implements ProfileSearchOutputBoundary {

    private final ProfileSearchViewModel profileSearchViewModel;
    private final ViewManagerModel viewManagerModel;
    private final OtherProfileViewModel otherProfileViewModel;
    private final FeedViewModel feedViewModel;

    public ProfileSearchPresenter(ProfileSearchViewModel profileSearchViewModel,
                                  ViewManagerModel viewManagerModel,
                                  OtherProfileViewModel otherProfileViewModel,
                                  FeedViewModel feedViewModel
    ) {
        this.profileSearchViewModel = profileSearchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.otherProfileViewModel = otherProfileViewModel;
        this.feedViewModel = feedViewModel;
    }

    @Override
    public void prepareSuccessView(ProfileSearchOutputData response) {
        // On success, switch to the Other Profile View.

        final OtherProfileState otherProfileState = otherProfileViewModel.getState();

        this.otherProfileViewModel.setState(otherProfileState);
        this.otherProfileViewModel.firePropertyChanged();

        this.viewManagerModel.setState(otherProfileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String ErrorMessage) {
        final ProfileSearchState profileSearchState = profileSearchViewModel.getState();
        profileSearchState.setErrorMessage(ErrorMessage);
        profileSearchViewModel.firePropertyChanged();
    }

    @Override
    public void switchToFeedView() {
        final FeedState feedState = feedViewModel.getState();
        this.feedViewModel.setState(feedState);
        this.feedViewModel.firePropertyChanged();

        viewManagerModel.setState(feedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
