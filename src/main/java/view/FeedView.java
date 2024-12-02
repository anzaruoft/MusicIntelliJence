package view;

import entity.User;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.feed.FeedController;
import interface_adapter.feed.FeedPresenter;
import interface_adapter.feed.FeedState;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileState;
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
    private final JTextArea postsArea;
    private final JScrollPane postsScrollPane;

    private final JButton addratingButton;
    private final JButton profileButton;
    private final JButton addfriendbutton;
    private final JButton changepasswordbutton;

    public FeedView(FeedViewModel feedViewModel) {
        this.feedViewModel = feedViewModel;
        this.feedViewModel.addPropertyChangeListener(this);

        postsArea = new JTextArea(15, 30);
        postsArea.setEditable(false);
        postsScrollPane = new JScrollPane(postsArea);

        final JLabel title = new JLabel("Feed");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        addratingButton = new JButton("Add song");
        buttons.add(addratingButton);

        profileButton = new JButton("Profile");
        buttons.add(profileButton);

        addfriendbutton = new JButton("Add friend");
        buttons.add(addfriendbutton);

        changepasswordbutton = new JButton("Change password");
        buttons.add(changepasswordbutton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setBackground(Color.PINK);
        buttons.setBackground(Color.PINK);

        addratingButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(addratingButton)) {
                            final FeedState currentState = feedViewModel.getState();
                            feedController.execute(feedViewModel.getState().getUsername());
                            feedPresenter.switchToSongSearchView(
                                    currentState.getUsername()
                            );
//                            System.out.println("Here are the users posts");
//                            System.out.println(feedViewModel.getState().getUsername());
//                            // feedViewModel.getState().getUser() is not working!
//                            System.out.println(feedViewModel.getState().getUser().getPosts());
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
        this.add(title);
        this.add(buttons);
        this.add(postsScrollPane);
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

    /**
     * React to a button click that results in evt.
     *
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }
}

