package interface_adapter.login;

import interface_adapter.ViewManagerModel;
<<<<<<< HEAD
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.song_search.SongSearchViewModel;
=======
import interface_adapter.feed.FeedState;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
>>>>>>> origin/main
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final FeedViewModel feedViewModel;
    private final ViewManagerModel viewManagerModel;
<<<<<<< HEAD
    private final SongSearchViewModel songSearchViewModel;
=======
    private final SignupViewModel signupViewModel;
>>>>>>> origin/main

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          FeedViewModel feedViewModel,
                          LoginViewModel loginViewModel, SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.feedViewModel = feedViewModel;
        this.loginViewModel = loginViewModel;
<<<<<<< HEAD
        this.songSearchViewModel = new SongSearchViewModel();
=======
        this.signupViewModel = signupViewModel;
>>>>>>> origin/main
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the feed view.

        final FeedState feedState = feedViewModel.getState();
        // feedState.setUsername(response.getUsername());
        this.feedViewModel.setState(feedState);
        this.feedViewModel.firePropertyChanged();

<<<<<<< HEAD
        // Switch to SongSearch view

        this.viewManagerModel.setState(songSearchViewModel.getViewName());
=======
        this.viewManagerModel.setState(feedViewModel.getViewName());
>>>>>>> origin/main
        this.viewManagerModel.firePropertyChanged();
//
//        this.viewManagerModel.setState(loggedInViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

    /**
     *
     */
    public void prepareSignupView() {
        final SignupState signupState = signupViewModel.getState();
        this.signupViewModel.setState(signupState);
        this.signupViewModel.firePropertyChanged();

        this.viewManagerModel.setState(signupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
