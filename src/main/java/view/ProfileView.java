package view;

import javax.swing.*;
import javax.swing.border.Border;
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
import org.json.JSONException;

public class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Profile";
    private final ProfileViewModel profileViewModel;
    private ProfileController profileController;
    private final JLabel friendsErrorField = new JLabel();
    private JLabel songsHeader;
    private FeedViewModel feedViewModel;
    private final JTextArea postsArea;
    private final JScrollPane postsScrollPane;
    private final JTextArea topSongsArea;
    private final JScrollPane topSongsScrollPane;

    public ProfileView(ProfileViewModel profileViewModel) {

        this.profileViewModel = profileViewModel;
        this.profileViewModel.addPropertyChangeListener(this);

        postsArea = new JTextArea(15, 30);
        postsArea.setEditable(false);
        postsScrollPane = new JScrollPane(postsArea);
        topSongsArea = new JTextArea(15, 30);
        topSongsArea.setEditable(false);
        topSongsScrollPane = new JScrollPane(postsArea);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Black border with 2px thickness
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
                        profileController.switchToFriendsView();
                    }
                }
        );
        topPanel.add(friendsButton);
        topPanel.add(friendsLabel);
        topPanel.setBackground(Color.PINK);
        topPanel.add(Box.createVerticalGlue());
        topPanel.setBorder(border);
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
//        songsHeader = new JLabel("Your Posts: ");
//        songsPanel.add(songsHeader);
//        posts = currentState.getPosts();

//        System.out.println("This is JSON format for posts:");
//        System.out.println(posts.toString());
//        if (!posts.isEmpty()) {
//
//            for (int i = 0; i < posts.length(); i++) {
//                String post = posts.getString(i);
//                JLabel item = new JLabel(post);
//                songsPanel.add(item);
//            }
//        }
//        else {
//            final JLabel songsError = new JLabel("Please add some posts!");
//            songsPanel.add(songsError);
//        }
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
//        this.add(new JLabel("My Top SONGS:"));
        this.add(postsArea);
        this.add(topSongsArea);

//        this.add(postsPanel);
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

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        final ProfileState state = (ProfileState) evt.getNewValue();
//        friendsErrorField.setText(state.getFriendsError());
//    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        profileController.execute(profileViewModel.getState().getUsername(), profileViewModel.getState().getUser());

        // Fetch the posts as a JSONArray
        JSONArray posts = profileViewModel.getState().getUser().getPosts();

        // Build a formatted string with only the relevant content
        StringBuilder formattedPosts = new StringBuilder();
        formattedPosts.append("My recent Posts:").append("\n");
        StringBuilder formattedTopSongs = new StringBuilder();
        formattedTopSongs.append("My Top Songs:").append("\n");
        if (posts != null && posts.length() > 0) {
            for (int i = 0; i < posts.length(); i++) {
                try {
                    String post = posts.getString(i); // Extract each post as a string
                    if (isValidPost(post)) {         // Check if the post is valid (not nested noise)
                        formattedPosts.append(post).append("\n"); // Append to the formatted output
                        String lastThree = post.substring(post.length() - 3);
                        String restOfString = post.substring(0, post.length() - 3);
                        String topRating = "5/5";
                        if (lastThree.equals(topRating)) {
                            formattedTopSongs.append(restOfString).append("\n");
                        }
                    }
                } catch (JSONException e) {
                    System.err.println("Error parsing post at index " + i + ": " + e.getMessage());
                }
            }
        } else {
            formattedPosts.append("No ratings available."); // Default message if no posts
            formattedTopSongs.append("No favorite songs just yet!.");
        }

        // Display the formatted posts in the text area
        postsArea.setText(formattedPosts.toString());
        postsArea.setCaretPosition(0);
        postsArea.setBackground(Color.PINK);
        topSongsArea.setText(formattedTopSongs.toString());
        topSongsArea.setCaretPosition(0);
        topSongsArea.setBackground(Color.PINK);

    }

    private boolean isValidPost(String post) {
        return post != null && !post.equals("[]") && !post.startsWith("[");
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

