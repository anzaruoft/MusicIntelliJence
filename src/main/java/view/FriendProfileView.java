package view;

import interface_adapter.friendProfile.FriendProfileController;
import interface_adapter.friendProfile.FriendProfilePresenter;
import interface_adapter.friendProfile.FriendProfileState;
import interface_adapter.friendProfile.FriendProfileViewModel;
import interface_adapter.friends.FriendsController;
import interface_adapter.friends.FriendsPresenter;
import org.json.JSONArray;

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

    private final String viewName = "friend profile";
    private final FriendProfileViewModel friendProfileViewModel;
    private FriendProfileController friendProfileController;
    private FriendProfilePresenter friendProfilePresenter;

    public FriendProfileView(FriendProfileViewModel friendProfileViewModel) {
        this.friendProfileViewModel = friendProfileViewModel;

        // Add property change listener
        friendProfileViewModel.addPropertyChangeListener(this);

        // Set layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.PINK);

        // Collect user information
        final JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.PINK);
        topPanel.add(Box.createVerticalGlue());

        final FriendProfileState currentState = friendProfileViewModel.getState();
        final String username = currentState.getUsername();
        final int friendsNumber = currentState.getFriendsNumber();
        final JSONArray posts = currentState.getPosts();
        final JSONArray topSongs = currentState.getTopSongs();

        // Set up followers / following

        final JLabel nameLabel = new JLabel(username);
        final JButton backButton = new JButton("Back");
        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        friendProfileController.switchToFriendsView();
                    }
                }
        );
        topPanel.add(backButton);
        topPanel.add(nameLabel);

        final JLabel friendsLabel = new JLabel(String.format("Friends: %d", friendsNumber));
        topPanel.add(friendsLabel);
        this.add(topPanel);

        // Add the top songs
        final JPanel songsPanel = new JPanel();
        songsPanel.add(Box.createVerticalGlue());
        songsPanel.setBackground(Color.PINK);

        final JLabel songsHeader = new JLabel("Top Songs: ");
        songsPanel.add(songsHeader);

        if (!topSongs.isEmpty()) {
            for (int i = 0; i < topSongs.length(); i++) {
                String topSong = topSongs.getString(i);
                JLabel songLabel = new JLabel(topSong);
                songsPanel.add(songLabel);
            }
        }
        else {
            final JLabel songsError = new JLabel("They don't have any songs reviewed yet!");
            songsPanel.add(songsError);
        }
        this.add(songsPanel);

        // Add the posts
        final JPanel postsPanel = new JPanel();
        postsPanel.add(Box.createVerticalGlue());
        postsPanel.setBackground(Color.PINK);

        final JLabel postsHeader = new JLabel("All Posts: ");
        postsPanel.add(postsHeader);

        if (!posts.isEmpty()) {
            for (int i = 0; i < posts.length(); i++) {
                String post = posts.getString(i);
                JLabel item = new JLabel(post);
                postsPanel.add(item);
            }
        }
        else {
            final JLabel postsError = new JLabel("They don't have any posts, check back later!");
            postsPanel.add(postsError);
        }
        this.add(postsPanel);
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

    public String getViewName() {
        return viewName;
    }

    public void setFriendProfileController(FriendProfileController friendProfileController) {
        this.friendProfileController = friendProfileController;
    }

    public void setFriendProfilePresenter(FriendProfilePresenter friendProfilePresenter) {
        this.friendProfilePresenter = friendProfilePresenter;
    }
}
