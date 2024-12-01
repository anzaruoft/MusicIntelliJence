package view;

import data_access.DBUserDataAccessObject;
import entity.User;
import interface_adapter.feed.FeedState;
import interface_adapter.leave_rating.LeaveRatingController;
import interface_adapter.leave_rating.LeaveRatingPresenter;
import interface_adapter.leave_rating.LeaveRatingState;
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
    private JTextField songTitleField;
    private JTextField ratingField;
    private DBUserDataAccessObject dbUserDataAccessObject;
    private LeaveRatingState leaveRatingState;

    public LeaveRatingView(LeaveRatingViewModel leaveRatingViewModel) {
        this.leaveRatingViewModel = leaveRatingViewModel;
        this.leaveRatingViewModel.addPropertyChangeListener(this);
        this.setLayout(new BorderLayout());

        // Panel for form inputs
        final JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.PINK);

        // Song Title
        final JLabel songTitleLabel = new JLabel("Song Title:");
        songTitleField = new JTextField(20);
        songTitleField.setEditable(false);
        // Leave a Rating

        final JLabel ratingLabel = new JLabel("Leave a Rating (1-5):");
        ratingField = new JTextField(5);
        ratingField.setEditable(false);

        // Buttons
        final JPanel buttonPanel = new JPanel();
        final JButton backButton = new JButton("Back");
        final JButton rateButton = new JButton("Rate");

        buttonPanel.setBackground(Color.PINK);

        // Adding components to the form panel
        formPanel.add(songTitleLabel);
        formPanel.add(songTitleField);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(ratingLabel);
        formPanel.add(ratingField);

        // Adding buttons to the button panel
        buttonPanel.add(backButton);
        buttonPanel.add(rateButton);

        // Adding panels to the main view
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners
        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        leaveRatingController.switchToSongSearchView();
                    }
                }
        );

        rateButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        System.out.println(leaveRatingViewModel.getState().getUsername());
                        leaveRatingController
                                .execute(leaveRatingViewModel.getState().getUsername(),
                                        songTitleField.getText(), ratingField.getText());
                        leaveRatingController.switchtoFeedView();
                    }
                }
        );
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final LeaveRatingState state = (LeaveRatingState) evt.getNewValue();
            songTitleField.setText(state.getSongTitle());
            ratingField.setText(state.getRating());
        }
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
