package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import data_access.DBUserDataAccessObject;
import interface_adapter.feed.FeedState;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.leave_rating.LeaveRatingState;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile.ProfileViewModel;
import org.json.JSONArray;

public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Profile";
    private final ProfileViewModel profileViewModel;
    private ProfileController profileController;
    private final JLabel friendsErrorField = new JLabel();
    private JLabel songsHeader;
    private FeedViewModel feedViewModel;

    public ProfileView(ProfileViewModel profileViewModel) {

        this.profileViewModel = profileViewModel;
        this.profileViewModel.addPropertyChangeListener(this);

        // Collect User Information
        final ProfileState currentState = profileViewModel.getState();
        final int friendsNumber = currentState.getFriendsNumber();
        JSONArray posts = currentState.getPosts();
        final JSONArray topSongs = currentState.getTopSongs();

        // Set up followers / following
        final JPanel topPanel = new JPanel();
        final JLabel nameLabel = new JLabel("My Profile");
        final JButton backButton = new JButton("Back");
        topPanel.add(backButton);
        topPanel.add(nameLabel);

        final JLabel friendsLabel = new JLabel(String.format("Friends: %d", friendsNumber));
        final JButton friendsButton = new JButton("View Friends");

        friendsButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final ProfileState currentState = profileViewModel.getState();
                        System.out.println(currentState.getUsername());
                        profileController.execute(currentState.getUsername());
                    }
                }
        );
        topPanel.add(friendsButton);
        topPanel.add(friendsLabel);
        topPanel.setBackground(Color.PINK);
        topPanel.add(Box.createVerticalGlue());
        this.add(topPanel);

        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        profileController.switchToFeedView();
                        System.out.println(profileViewModel.getState().getUser().getName());
                    }
                }
        );

        // Add your posts
        final JPanel songsPanel = new JPanel();
        songsHeader = new JLabel("Your Posts: ");
        songsPanel.add(songsHeader);
        posts = currentState.getPosts();

//        System.out.println("This is JSON format for posts:");
//        System.out.println(posts.toString());
        if (!posts.isEmpty()) {

            for (int i = 0; i < posts.length(); i++) {
                String post = posts.getString(i);
                JLabel item = new JLabel(post);
                songsPanel.add(item);
            }
        }
        else {
            final JLabel songsError = new JLabel("Please add some posts!");
            songsPanel.add(songsError);
        }
        songsPanel.add(Box.createVerticalGlue());
        songsPanel.setBackground(Color.PINK);
        this.add(songsPanel);

        // Add the posts
        final JPanel postsPanel = new JPanel();
        final JLabel postsHeader = new JLabel("All Posts: ");
        postsPanel.add(postsHeader);
        if (!posts.isEmpty()) {

            for (int i = 0; i < posts.length(); i++) {
                String post = posts.getString(i);
                JLabel item = new JLabel(post);
                postsPanel.add(item);
            }
        } else {
            final JLabel postsError = new JLabel("Please write some posts to see them here!");
            postsPanel.add(postsError);
        }
        postsPanel.add(Box.createVerticalGlue());
        postsPanel.setBackground(Color.PINK);
        this.add(postsPanel);
        this.setBackground(Color.PINK);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public String getViewName() {
        return viewName;
    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        if ("state".equals(evt.getPropertyName())) {
//            final ProfileState state = (ProfileState) evt.getNewValue();
//            songsHeader.setText(state.toString());
//            friendsErrorField.setText(state.getFriendsError());
//        }
//    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ProfileState state = (ProfileState) evt.getNewValue();
        friendsErrorField.setText(state.getFriendsError());
    }

    /**
     * React to a button click that results in evt.
     *
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }
}

