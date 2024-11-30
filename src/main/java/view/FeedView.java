package view;

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
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(addratingButton)) {
                        final FeedState currentState = feedViewModel.getState();
                    }
                }
        );

        addratingButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(addratingButton)) {
                            final FeedState currentState = feedViewModel.getState();

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

                            feedPresenter.switchToProfileView(
                                    currentState.getUsername()
                            );
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final FeedState state = (FeedState) evt.getNewValue();

            postsArea.setText("");
            if (state.getPosts() != null && !state.getPosts().isEmpty()) {
                for (String post : state.getPosts()) {
                    postsArea.append(post + "\n");
                }
            }
            else {
                postsArea.setText("No ratings available.");
                postsArea.setCaretPosition(0);
            }

        }
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

