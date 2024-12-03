package view;

import interface_adapter.friends.FriendsController;
import interface_adapter.friends.FriendsPresenter;
import interface_adapter.friends.FriendsViewModel;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FriendsView extends JPanel implements PropertyChangeListener {

    private final String viewName = "friends";
    private final FriendsViewModel friendsViewModel;
    private FriendsController friendsController;
    private final JPanel friendsPanel;
    private FriendsPresenter friendsPresenter;

    public FriendsView(FriendsViewModel friendsViewModel) {
        this.friendsViewModel = friendsViewModel;

        // Set layout and background
        this.setLayout(new BorderLayout());
        this.setBackground(Color.PINK);

        // Create header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.PINK); // Light pink
        JLabel titleLabel = new JLabel("Friends List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        this.add(headerPanel, BorderLayout.NORTH);

        // Create friends panel to hold friend buttons
        friendsPanel = new JPanel();
        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
        friendsPanel.setBackground(Color.PINK);
        JScrollPane scrollPane = new JScrollPane(friendsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.CENTER);

        // Add Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> friendsController.backToProfileView());
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.PINK);
        footerPanel.add(backButton);
        this.add(footerPanel, BorderLayout.SOUTH);

        // Register this view as a listener for property changes
        friendsViewModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("THIS NEVER HAPPENS");
        if ("friends".equals(evt.getPropertyName())) {
            // Ensure the UI update happens on the Event Dispatch Thread
            SwingUtilities.invokeLater(this::updateFriendsList);
            System.out.println("THIS NEVER HAPPENS");
        }
    }

    private void updateFriendsList() {
        // Clear the panel
        friendsPanel.removeAll();

        // Fetch the friends from the ViewModel
        JSONArray friends = friendsViewModel.getState().getUser().getFriends();
        System.out.println("COMMAND F ME");
        System.out.println(friends);
        if (friends != null && friends.length() > 0) {
            for (int i = 0; i < friends.length(); i++) {
                JSONObject friend = friends.getJSONObject(i);
                String username = friend.getString("username");

                // Create a panel for each friend
                JPanel friendPanel = new JPanel(new BorderLayout());
                friendPanel.setMaximumSize(new Dimension(400, 50));
                friendPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                // Add username label
                JLabel nameLabel = new JLabel(username);
                nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                friendPanel.add(nameLabel, BorderLayout.CENTER);

                // Add profile button
                JButton profileButton = new JButton("View Profile");
                profileButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Viewing profile for: " + username);
                        friendsController.switchToFriendProfile(username); // Pass the username to the controller
                    }
                });
                friendPanel.add(profileButton, BorderLayout.EAST);

                // Add friend panel to the main friends panel
                friendsPanel.add(friendPanel);
            }
        } else {
            // Add a label if there are no friends
            JLabel noFriendsLabel = new JLabel("No friends yet! Add some and check again.", SwingConstants.CENTER);
            noFriendsLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            noFriendsLabel.setForeground(Color.GRAY);
            friendsPanel.add(noFriendsLabel);
        }

        // Revalidate and repaint the friends panel and the entire view
        friendsPanel.revalidate();
        friendsPanel.repaint();
        this.revalidate();  // Ensures the entire view is updated
        this.repaint(); // Repaints the view to reflect changes
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
