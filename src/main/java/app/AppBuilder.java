package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DBUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.feed.FeedController;
import interface_adapter.feed.FeedPresenter;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.friendProfile.FriendProfileController;
import interface_adapter.friendProfile.FriendProfilePresenter;
import interface_adapter.friendProfile.FriendProfileViewModel;
import interface_adapter.friends.FriendsController;
import interface_adapter.friends.FriendsPresenter;
import interface_adapter.friends.FriendsViewModel;
import interface_adapter.leave_rating.LeaveRatingController;
import interface_adapter.leave_rating.LeaveRatingPresenter;
import interface_adapter.leave_rating.LeaveRatingViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.song_search.SongSearchController;
import interface_adapter.song_search.SongSearchPresenter;
import interface_adapter.song_search.SongSearchViewModel;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.feed.FeedInputBoundary;
import use_case.feed.FeedInteractor;
import use_case.friendProfile.FriendProfileInputBoundary;
import use_case.friendProfile.FriendProfileInteractor;
import use_case.friends.FriendsInputBoundary;
import use_case.friends.FriendsInteractor;
import use_case.friends.FriendsOutputBoundary;
import use_case.leave_rating.LeaveRatingInputBoundary;
import use_case.leave_rating.LeaveRatingInteractor;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInteractor;
import use_case.profile.ProfileOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.song_search.SongSearchInputBoundary;
import use_case.song_search.SongSearchInteractor;
import use_case.song_search.SongSearchOutputBoundary;
import view.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userFactory);

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private FeedView feedView;
    private FeedViewModel feedViewModel;
    private ProfileViewModel profileViewModel;
    private ProfileView profileView;
    private FriendsViewModel friendsViewModel;
    private FriendsView friendsView;
    private FriendProfileViewModel friendProfileViewModel;
    private FriendProfileView friendProfileView;
    private SongSearchViewModel songSearchViewModel;
    private SongSearchView songSearchView;
    private LeaveRatingViewModel leaveRatingViewModel;
    private LeaveRatingView leaveRatingView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    public AppBuilder addFeedView() {
        feedViewModel = new FeedViewModel();
        feedView = new FeedView(feedViewModel);
        cardPanel.add(feedView, feedView.getViewName());
        return this;
    }

    public AppBuilder addProfileView() {
        profileViewModel = new ProfileViewModel();
        profileView = new ProfileView(profileViewModel);
        cardPanel.add(profileView, profileView.getViewName());
        return this;
    }

    public AppBuilder addFriendsView() {
        friendsViewModel = new FriendsViewModel();
        friendsView = new FriendsView(friendsViewModel);
        cardPanel.add(friendsView, friendsView.getViewName());
        return this;
    }

    public AppBuilder addFriendProfileView() {
        friendProfileViewModel = new FriendProfileViewModel();
        friendProfileView = new FriendProfileView(friendProfileViewModel);
        cardPanel.add(friendProfileView, friendProfileView.getViewName());
        return this;
    }

    public AppBuilder addSongSearchView() {
        songSearchViewModel = new SongSearchViewModel();
        songSearchView = new SongSearchView(songSearchViewModel);
        cardPanel.add(songSearchView, songSearchView.getViewName());
        return this;
    }

    public AppBuilder addLeaveRatingView() {
        leaveRatingViewModel = new LeaveRatingViewModel();
        leaveRatingView = new LeaveRatingView(leaveRatingViewModel);
        cardPanel.add(leaveRatingView, leaveRatingView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginPresenter loginPresenter = new LoginPresenter(viewManagerModel,
                feedViewModel, loginViewModel, signupViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginPresenter);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        loginView.setLoginPresenter(loginPresenter);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(loggedInViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    public AppBuilder addFeedUseCase() {
        final FeedPresenter feedPresenter = new FeedPresenter(viewManagerModel,
                feedViewModel, profileViewModel, songSearchViewModel);
        final FeedInputBoundary feedInteractor = new FeedInteractor(userDataAccessObject, feedPresenter);

        final FeedController feedController = new FeedController(feedInteractor);
        feedView.setFeedController(feedController);
        feedView.setFeedPresenter(feedPresenter);
        return this;
    }

    public AppBuilder addProfileUseCase() {
        final ProfileOutputBoundary profileOutputPresenter = new ProfilePresenter(viewManagerModel, profileViewModel, feedViewModel);
        final ProfileInputBoundary profileInteractor = new ProfileInteractor(userDataAccessObject, profileOutputPresenter);
        final ProfileController profileController = new ProfileController(profileInteractor);
        profileView.setProfileController(profileController);
        feedView.setProfileController(profileController);
        return this;
    }

    public AppBuilder addFriendsUseCase() {
        final FriendsPresenter friendsPresenter = new FriendsPresenter(viewManagerModel,
                profileViewModel, friendsViewModel);
        final FriendsInputBoundary friendsInteractor = new FriendsInteractor(userDataAccessObject, friendsPresenter);

        final FriendsController friendsController = new FriendsController(friendsInteractor);
        friendsView.setFriendsController(friendsController);
        friendsView.setFriendsPresenter(friendsPresenter);
        return this;
    }

    public AppBuilder addFriendProfileUseCase() {
        final FriendProfilePresenter friendProfilePresenter = new FriendProfilePresenter(viewManagerModel,
                friendProfileViewModel, friendsViewModel);
        final FriendProfileInputBoundary friendProfileInteractor = new FriendProfileInteractor(userDataAccessObject,
                friendProfilePresenter);

        final FriendProfileController friendProfileController = new FriendProfileController(friendProfileInteractor);
        friendProfileView.setFriendProfileController(friendProfileController);
        friendProfileView.setFriendProfilePresenter(friendProfilePresenter);
        return this;
    }

    public AppBuilder addSongSearchUseCase() {
        final SongSearchPresenter songSearchPresenter = new SongSearchPresenter(viewManagerModel,
                songSearchViewModel, feedViewModel);
        final SongSearchInputBoundary songSearchInteractor = new SongSearchInteractor(userDataAccessObject,
                songSearchPresenter);

        final SongSearchController songSearchController = new SongSearchController(songSearchInteractor);
        songSearchView.setSongSearchController(songSearchController);
        songSearchView.setSongSearchPresenter(songSearchPresenter);
        feedView.setSongSearchController(songSearchController);

        return this;
    }

    public AppBuilder addLeaveRatingUseCase() {
        final LeaveRatingPresenter leaveRatingPresenter = new LeaveRatingPresenter(viewManagerModel,
                leaveRatingViewModel);
        final LeaveRatingInputBoundary leaveRatingInteractor = new LeaveRatingInteractor(userDataAccessObject,
                leaveRatingPresenter);

        final LeaveRatingController leaveRatingController = new LeaveRatingController(leaveRatingInteractor);
        leaveRatingView.setLeaveRatingController(leaveRatingController);
        leaveRatingView.setLeaveRatingPresenter(leaveRatingPresenter);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("InTune");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }

}
