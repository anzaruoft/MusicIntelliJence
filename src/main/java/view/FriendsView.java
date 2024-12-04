package view;


import interface_adapter.friends.FriendsController;
import interface_adapter.friends.FriendsPresenter;
import interface_adapter.friends.FriendsState;
import interface_adapter.friends.FriendsViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.*;

public class FriendsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "friends";
    private final FriendsViewModel friendsViewModel;
    private FriendsController friendsController;
    private FriendsPresenter friendsPresenter;

    public FriendsView(FriendsViewModel friendsViewModel) {
        this.friendsViewModel = friendsViewModel;

        this.setBackground(Color.PINK);
        final JPanel headerPanel = new JPanel();
        final JLabel title = new JLabel("Friends");
        headerPanel.add(title);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.add(Box.createVerticalGlue());
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.setBackground(Color.PINK);
        this.add(headerPanel);
        final JPanel friendsPanel = new JPanel();
        friendsPanel.setBackground(Color.PINK);
        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
        friendsPanel.add(Box.createVerticalGlue());

        final FriendsState currentState = friendsViewModel.getState();
        final List<String> friendsList = currentState.getFriends();
        if (!friendsList.isEmpty()) {
            for (String friend : friendsList) {
                // Display the name of each friend
                JLabel friendName = new JLabel(friend);
                friendsPanel.add(friendName);

                // Create a button that will link to their profile
                JButton profileButton = new JButton("View Profile");
                profileButton.setActionCommand(friend);
                profileButton.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                final FriendsState currentState = friendsViewModel.getState();
                                friendsController.execute(currentState.getUsername());
                            }
                        }
                );
                friendsPanel.add(profileButton);
            }
        }
        else {
            friendsPanel.add(new JLabel("No friends to display."));
        }
        final JButton backButton = new JButton("Back");
        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        friendsController.backToProfileView();
                    }
                }
        );
        this.add(friendsPanel);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes here
        System.out.println("Property changed: " + evt.getPropertyName());
    }

    public String getViewName() {
        return viewName;
    }

    public void setFriendsController(FriendsController friendsController) {
        this.friendsController = friendsController;
    }

    public void setFriendsPresenter(FriendsPresenter friendsPresenter) {
        this.friendsPresenter = friendsPresenter;
    }

}