package app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */

public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
<<<<<<< HEAD
                                            .addSongSearchView()
=======
                                            .addFeedView()
                                            .addProfileView()
                                            .addFriendsView()
                                            .addFriendProfileView()
>>>>>>> origin/main
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addChangePasswordUseCase()
                                            .addLogoutUseCase()
<<<<<<< HEAD
                                            .addSongSearchUseCase()
=======
                                            .addFeedUseCase()
                                            .addProfileUseCase()
                                            .addFriendsUseCase()
                                            .addFriendProfileUseCase()
>>>>>>> origin/main
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
