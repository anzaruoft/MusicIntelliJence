package view;

import interface_adapter.other_profile.OtherProfileController;
import interface_adapter.other_profile.OtherProfileState;
import interface_adapter.other_profile.OtherProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The ViewModel for the Other Profile View.
 */
public class OtherProfileView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "other profile";
    private final OtherProfileViewModel otherProfileViewModel;
    private OtherProfileController otherProfilecontroller;
    private final JLabel usernameErrorField = new JLabel();

    public OtherProfileView(OtherProfileViewModel otherProfileViewModel) {

        this.otherProfileViewModel = otherProfileViewModel;
        this.otherProfileViewModel.addPropertyChangeListener(this);

        // Collect User Information
        final OtherProfileState currentState = otherProfileViewModel.getState();
        final int friendsCount = currentState.getFriendsCount();

        // Set up Labels and Add friend button
        final JPanel topPanel = new JPanel();
//        final JLabel nameLabel = new JLabel("Profile of " + currentState.getOtherUsername());
        final JLabel nameLabel = new JLabel("Profile Exists");
        final JButton backButton = new JButton("Back");
        topPanel.add(nameLabel);
        topPanel.add(backButton);

        final JLabel friendsCountLabel = new JLabel(String.format("Friends: %d", friendsCount));

        final JButton addButton = new JButton("Add");
        addButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final OtherProfileState currentState = otherProfileViewModel.getState();
                        System.out.println(currentState.getUsername());
                        System.out.println(currentState.getOtherUsername());
                        otherProfilecontroller.execute(currentState.getOtherUsername());
                    }
                }
        );
//        topPanel.add(addButton);
        topPanel.setBackground(Color.PINK);
        topPanel.add(Box.createVerticalGlue());
        this.add(topPanel);

        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        otherProfilecontroller.switchToFeedView();
                    }
                }
        );
        this.setBackground(Color.PINK);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final OtherProfileState state = (OtherProfileState) evt.getNewValue();
        usernameErrorField.setText(state.getUsernameError());
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public void setOtherProfileController(OtherProfileController otherProfileController) {
        this.otherProfilecontroller = otherProfileController;
    }
}

