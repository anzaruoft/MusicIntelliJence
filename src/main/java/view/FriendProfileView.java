package view;

import interface_adapter.friendProfile.FriendProfileController;
import interface_adapter.friendProfile.FriendProfileState;
import interface_adapter.friendProfile.FriendProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * The View for the Friend Profile Use Case.
 */
public class FriendProfileView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Friend profile";
    private final FriendProfileViewModel friendProfileViewModel;
    private final FriendProfileController friendProfileController;

    public FriendProfileView(FriendProfileViewModel friendProfileViewModel, FriendProfileController friendProfileController) {
        this.friendProfileViewModel = friendProfileViewModel;
        this.friendProfileController = friendProfileController;

        // Add property change listener
        friendProfileViewModel.addPropertyChangeListener(this);

        // Set layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Collect user information
        final FriendProfileState currentState = friendProfileViewModel.getState();
        final int friendsNumber = currentState.getFriendsNumber();
        final List<String> posts = currentState.getPosts();
        final List<String> topSongs = currentState.getTopSongs();

        // Set up followers / following
        final JLabel nameLabel = new JLabel("My Profile ");
        final JLabel friendsLabel = new JLabel(String.format("Friends: %d", friendsNumber));

        this.add(nameLabel);
        this.add(friendsLabel);

        // Add the top songs
        final JLabel songsHeader = new JLabel("Top Songs: ");
        this.add(songsHeader);

        if (!topSongs.isEmpty()) {
            for (String topSong : topSongs) {
                final JLabel songLabel = new JLabel(topSong);
                this.add(songLabel);
            }
        }
        else {
            final JLabel songsError = new JLabel("They don't have any songs reviewed yet!");
            this.add(songsError);
        }

        // Add the posts
        final JLabel postsHeader = new JLabel("All Posts: ");
        this.add(postsHeader);

        if (!posts.isEmpty()) {
            for (String post : posts) {
                final JLabel postLabel = new JLabel(post);
                this.add(postLabel);
            }
        }
        else {
            final JLabel postsError = new JLabel("They don't have any posts, check back later!");
            this.add(postsError);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes here
        System.out.println("Property changed: " + evt.getPropertyName());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action performed: " + e.getActionCommand());
    }
}
