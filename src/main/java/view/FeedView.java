package view;

import interface_adapter.change_password.LoggedInState;
import interface_adapter.feed.FeedController;
import interface_adapter.feed.FeedState;
import interface_adapter.feed.FeedViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FeedView extends JPanel implements PropertyChangeListener {
    private final String viewName = "feed";
    private final FeedViewModel feedViewModel;
    private FeedController feedController;


    private final JButton addratingButton;
    private final JButton profileButton;
    private final JButton addfriendbutton;


    public FeedView(FeedViewModel feedViewModel) {
        this.feedViewModel = feedViewModel;
        // this.feedViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Feed");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        addratingButton = new JButton("Add song");
        buttons.add(addratingButton);

        profileButton = new JButton("Profile");
        buttons.add(profileButton);

        addfriendbutton = new JButton("Add friend");
        buttons.add(addfriendbutton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setBackground(Color.PINK);

        addratingButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(addratingButton)) {
                        final FeedState currentState = feedViewModel.getState();
                    }
                }
        );

        profileButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(profileButton)) {
                        final FeedState currentState = feedViewModel.getState();
                    }
                }
        );

        this.add(title);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    public String getViewName() {
        return viewName;
    }

    public void setFeedController(FeedController feedController) {
        this.feedController = feedController;
    }

}

