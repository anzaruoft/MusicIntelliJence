package view;

import entity.User;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.feed.FeedController;
import interface_adapter.feed.FeedPresenter;
import interface_adapter.feed.FeedState;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.other_profile.OtherProfileController;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileState;
import interface_adapter.profile_search.ProfileSearchController;
import interface_adapter.song_search.SongSearchController;
import interface_adapter.song_search.SongSearchPresenter;
import interface_adapter.song_search.SongSearchState;
import org.json.JSONArray;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FeedView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "feed";
    private final FeedViewModel feedViewModel;
    private FeedController feedController;
    private ProfileController profileController;
    private FeedPresenter feedPresenter;
    private SongSearchController songSearchController;
    private ChangePasswordController changePasswordController;
    private ProfileSearchController profileSearchController;
    private OtherProfileController otherProfileController;
    private final JTextArea postsArea;
    private final JScrollPane postsScrollPane;

    private final JButton addratingButton;
    private final JButton profileButton;
    private final JButton profilesearchbutton;
    private final JButton changepasswordbutton;

    public FeedView(FeedViewModel feedViewModel) {
        this.feedViewModel = feedViewModel;
        this.feedViewModel.addPropertyChangeListener(this);

        // Text area for posts
        postsArea = new JTextArea(15, 30);
        postsArea.setEditable(false);
        postsScrollPane = new JScrollPane(postsArea);

        // Title
        JLabel title = new JLabel("InTune");
        title.setFont(new Font("Arial", Font.BOLD, 32)); // Large, bold font
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons
        addratingButton = new JButton("Add song");
        profileButton = new JButton("Profile");
        changepasswordbutton = new JButton("Change password");
        profilesearchbutton = new JButton("Profile search");

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.PINK);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonsPanel.add(addratingButton);
        buttonsPanel.add(profileButton);
        buttonsPanel.add(changepasswordbutton);
        buttonsPanel.add(profilesearchbutton);

        // Set layout for the main panel
        this.setLayout(new BorderLayout());
        this.setBackground(Color.PINK);

//public class FeedView extends JPanel implements ActionListener, PropertyChangeListener {
//    private final String viewName = "feed";
//    private final FeedViewModel feedViewModel;
//    private FeedController feedController;
//    private ProfileController profileController;
//    private FeedPresenter feedPresenter;
//    private SongSearchController songSearchController;
//    private ChangePasswordController changePasswordController;
//    private ProfileSearchController profileSearchController;
//    private OtherProfileController otherProfileController;
//    private final JTextArea postsArea;
//    private final JScrollPane postsScrollPane;
//
//    private final JButton addratingButton;
//    private final JButton profileButton;
//    private final JButton profilesearchbutton;
//    private final JButton changepasswordbutton;
//
//    public FeedView(FeedViewModel feedViewModel) {
//        this.feedViewModel = feedViewModel;
//        this.feedViewModel.addPropertyChangeListener(this);
//
//        postsArea = new JTextArea(15, 30);
//        postsArea.setEditable(false);
//        postsScrollPane = new JScrollPane(postsArea);
//
//        final JLabel title = new JLabel("Feed");
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        final JPanel buttons = new JPanel();
//        addratingButton = new JButton("Add song");
//        buttons.add(addratingButton);
//
//        profileButton = new JButton("Profile");
//        buttons.add(profileButton);
//
//        changepasswordbutton = new JButton("Change password");
//        buttons.add(changepasswordbutton);
//
//        profilesearchbutton = new JButton("Profile search");
//        buttons.add(profilesearchbutton);
//
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        this.setBackground(Color.PINK);
//        buttons.setBackground(Color.PINK);

        addratingButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(addratingButton)) {
                            final FeedState currentState = feedViewModel.getState();
                            feedController.execute(feedViewModel.getState().getUsername());
                            feedPresenter.switchToSongSearchView(
                                    currentState.getUsername()
                            );
                        }
                    }
                }
        );

        profileButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(profileButton)) {
                            final FeedState currentState = feedViewModel.getState();
                            feedController.execute(feedViewModel.getState().getUsername());
                            feedPresenter.switchToProfileView(
                                    currentState.getUsername()
                            );
                            System.out.println("Profile button is clicked");
                            System.out.println(feedViewModel.getState().getUser().getPosts());
                        }
                    }
                }
        );

        changepasswordbutton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        changePasswordController.switchToChangePasswordView();
                    }
                }
        );

        profilesearchbutton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(profilesearchbutton)) {
                            final FeedState currentState = feedViewModel.getState();
                            feedController.execute(feedViewModel.getState().getUsername());
                            feedPresenter.switchToProfileSearchView(currentState.getUsername());
                        }
                    }
                }
        );

        // Add components
        this.add(title, BorderLayout.NORTH); // Title at the top
        this.add(postsScrollPane, BorderLayout.CENTER); // Scrollable text area in the middle
        this.add(buttonsPanel, BorderLayout.SOUTH); // Buttons at the bottom
    }

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
////        if ("state".equals(evt.getPropertyName())) {
////            final FeedState state = (FeedState) evt.getNewValue();
////
////            postsArea.setText("");
////            if (state.getPosts() != null && !state.getPosts().isEmpty()) {
////                for (Object post : state.getPosts()) {
////                    postsArea.append(post + "\n");
////                }
////            } else {
////                postsArea.setText("No ratings available.");
////                postsArea.setCaretPosition(0);
//        feedController.execute(feedViewModel.getState().getUsername());
//        String rawPosts = feedViewModel.getState().getUser().getPosts().toString();
//        String formattedPosts = RemoveBackSlashes(rawPosts);
//        postsArea.setText(formattedPosts);
//    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        feedController.execute(feedViewModel.getState().getUsername());

        // Fetch the posts as a JSONArray
        JSONArray posts = feedViewModel.getState().getUser().getPosts();

        // Build a formatted string with only the relevant content
        StringBuilder formattedPosts = new StringBuilder();
        if (posts != null && posts.length() > 0) {
            for (int i = 0; i < posts.length(); i++) {
                try {
                    String post = posts.getString(i); // Extract each post as a string
                    if (isValidPost(post)) {         // Check if the post is valid (not nested noise)
                        formattedPosts.append(post).append("\n"); // Append to the formatted output
                    }
                } catch (JSONException e) {
                    System.err.println("Error parsing post at index " + i + ": " + e.getMessage());
                }
            }
        } else {
            formattedPosts.append("No ratings available."); // Default message if no posts
        }

        // Display the formatted posts in the text area
        postsArea.setText(formattedPosts.toString());
        postsArea.setCaretPosition(0);
    }

    private boolean isValidPost(String post) {
        return post != null && !post.equals("[]") && !post.startsWith("[");
    }

    public String getViewName() {
        return viewName;
    }

    public void setFeedController(FeedController feedController) {
        this.feedController = feedController;
    }

    public void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }

    public void setSongSearchController(SongSearchController songSearchController) {
        this.songSearchController = songSearchController;
    }

    public void setFeedPresenter(FeedPresenter feedPresenter) {
        this.feedPresenter = feedPresenter;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setProfileSearchController(ProfileSearchController profileSearchController) {
        this.profileSearchController = profileSearchController;
    }

    public void setOtherProfileController(OtherProfileController otherProfileController) {
        this.otherProfileController = otherProfileController;
    }

    /**
     * React to a button click that results in evt.
     *
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }
}

