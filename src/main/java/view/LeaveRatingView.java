package view;

import interface_adapter.leave_rating.LeaveRatingController;
import interface_adapter.leave_rating.LeaveRatingPresenter;
import interface_adapter.leave_rating.LeaveRatingViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LeaveRatingView extends JPanel implements PropertyChangeListener, ActionListener {
    private final String viewName = "leave rating";
    private final LeaveRatingViewModel leaveRatingViewModel;
    private LeaveRatingController leaveRatingController;

    public LeaveRatingView(LeaveRatingViewModel leaveRatingViewModel) {
        this.leaveRatingViewModel = leaveRatingViewModel;
        this.setLayout(new BorderLayout());

        // Panel for form inputs
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        // Song Title
        JLabel songTitleLabel = new JLabel("Song Title:");
        JTextField songTitleField = new JTextField(20);

        // Leave a Rating
        JLabel ratingLabel = new JLabel("Leave a Rating (1-5):");
        JTextField ratingField = new JTextField(5);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        JButton submitButton = new JButton("Save/Submit");

        // Adding components to the form panel
        formPanel.add(songTitleLabel);
        formPanel.add(songTitleField);
        formPanel.add(Box.createVerticalStrut(10)); // Adds spacing
        formPanel.add(ratingLabel);
        formPanel.add(ratingField);

        // Adding buttons to the button panel
        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);

        // Adding panels to the main view
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners
        // TODO
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes from the ViewModel if needed
    }

    public String getViewName() {
        return viewName;
    }

    public void setLeaveRatingController(LeaveRatingController leaveRatingController) {
        this.leaveRatingController = leaveRatingController;
    }

    public void setLeaveRatingPresenter(LeaveRatingPresenter leaveRatingPresenter) {
        // Implement if presenter logic is needed
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }
}
