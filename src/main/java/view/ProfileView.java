package view;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.friends.FriendsController;
import interface_adapter.profile.ProfileViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileState;

public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "ProfileView";
    private final ProfileViewModel profileViewModel;
    private ProfileController profileController;
    private FriendsController friendsController;

    public ProfileView(ProfileViewModel profileViewModel) {

        this.profileViewModel = profileViewModel;
        this.profileViewModel.addPropertyChangeListener(this);

        // Profile Description
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Collect User Information
        final ProfileState currentState = profileViewModel.getState();
        final int friendsNumber = currentState.getFriendsNumber();
        final List<String> posts = currentState.getPosts();
        final List<String> topSongs = currentState.getTopSongs();

        // Set up followers / following
        final JLabel nameLabel = new JLabel("My Profile");
        final JLabel friendsLabel = new JLabel(String.format("Friends: %d", friendsNumber));
        final JButton friendsButton = new JButton("View Friends");
        final JButton backButton = new JButton("Back");
        this.add(nameLabel);
        this.add(friendsLabel);
        friendsButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(friendsButton)) {
                            final ProfileState currentState = profileViewModel.getState();

                            friendsController.execute(
                                    currentState.getUsername()
                            );
                        }
                    }
                }
        );

        this.add(friendsButton);
        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        profileController.switchToLoggedInView();
                    }
                }
        );

        // Add the top songs
        final JLabel songsHeader = new JLabel("Top Songs: ");
        this.add(songsHeader);
        if (!topSongs.isEmpty()) {

            for (String topSong : topSongs) {
                final JLabel item = new JLabel(topSong);
                this.add(item);
            }
        }
        else {
            final JLabel songsError = new JLabel("Please add some songs to see your top!");
            this.add(songsError);
        }

        // Add the posts
        final JLabel postsHeader = new JLabel("All Posts: ");
        this.add(postsHeader);
        if (!posts.isEmpty()) {

            for (String post : posts) {
                final JLabel item = new JLabel(post);
                this.add(item);
            }
        }
        else {
            final JLabel postsError = new JLabel("Please write some posts to see them here!");
            this.add(postsError);
        }
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ProfileState state = (ProfileState) evt.getNewValue();
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }

    public void setFriendsController(FriendsController friendsController) {
        this.friendsController = friendsController;
    }
}
