package view;

import interface_adapter.other_profile.OtherProfileController;
import interface_adapter.other_profile.OtherProfilePresenter;
import interface_adapter.other_profile.OtherProfileState;
import interface_adapter.other_profile.OtherProfileViewModel;

import javax.print.attribute.standard.MediaSize;
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

    // ADDED
    private JLabel responseField = new JLabel();
    private final JLabel nameField = new JLabel();
    private final JLabel friendsCountField = new JLabel();
    private final JButton addButton;
    private final JButton backButton;

    private OtherProfileController otherProfileController;
    private OtherProfilePresenter otherProfilePresenter;
    private OtherProfileState otherProfileState;

    public OtherProfileView(OtherProfileViewModel otherProfileViewModel) {

        this.otherProfileViewModel = otherProfileViewModel;
        this.otherProfileViewModel.addPropertyChangeListener(this);

        // Title of the page
        final JLabel title = new JLabel("Other Profile");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons
        final JPanel buttons = new JPanel();
        addButton = new JButton("Add Friend");
        buttons.add(addButton);
        backButton = new JButton("Back");
        buttons.add(backButton);

        // Add Friend button execution.
        addButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addButton)) {
                            final OtherProfileState currentState = otherProfileViewModel.getState();
                            otherProfileController.execute(currentState.getSearchedUsername(),
                                    currentState.getThisUsername());
                        }
                    }
                }
        );

        // Back Button Execution
        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        otherProfileController.switchToFeedView();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons, BorderLayout.SOUTH);
        this.add(responseField, BorderLayout.EAST);

        this.setBackground(Color.PINK);
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
        final OtherProfileState state = (OtherProfileState) evt.getNewValue();
        responseField.setText(state.getResponse());
        nameField.setText(state.getSearchedUsername());
        friendsCountField.setText(String.valueOf(state.getFriendsCount()));
    }

    public void setOtherProfileController(OtherProfileController otherProfileController) {
        this.otherProfileController = otherProfileController;
    }

    public String getViewName() {
        return viewName;
    }
}

