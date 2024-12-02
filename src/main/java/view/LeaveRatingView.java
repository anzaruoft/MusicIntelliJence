package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.leave_rating.LeaveRatingController;
import interface_adapter.leave_rating.LeaveRatingPresenter;
import interface_adapter.leave_rating.LeaveRatingState;
import interface_adapter.leave_rating.LeaveRatingViewModel;

/**
 * This is the LeaveRatingView class.
 */
public class LeaveRatingView extends JPanel implements PropertyChangeListener, ActionListener {
    private static final int SONG_TITLE_FIELD_CONSTANT = 20;
    private static final int RATING_FIELD_CONSTANT = 5;
    private static final int CONSTANT_TEN = 10;
    private final String viewName = "leave rating";
    private final LeaveRatingViewModel leaveRatingViewModel;
    private LeaveRatingController leaveRatingController;
    private JTextField songTitleField;
    private JTextField ratingField;

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
        songTitleField = new JTextField(SONG_TITLE_FIELD_CONSTANT);
        songTitleField.setEditable(false);
        // Leave a Rating

        final JLabel ratingLabel = new JLabel("Leave a Rating (1-5):");
        ratingField = new JTextField(RATING_FIELD_CONSTANT);
        ratingField.setEditable(false);

        // Buttons
        final JPanel buttonPanel = new JPanel();
        final JButton backButton = new JButton("Back");
        final JButton rateButton = new JButton("Rate");

        buttonPanel.setBackground(Color.PINK);

        // Adding components to the form panel
        formPanel.add(songTitleLabel);
        formPanel.add(songTitleField);
        formPanel.add(Box.createVerticalStrut(CONSTANT_TEN));
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

    /**
     * This is the setLeaveRatingPresenter function.
     * @param leaveRatingPresenter is a parameter.
     */
    public void setLeaveRatingPresenter(LeaveRatingPresenter leaveRatingPresenter) {
        // Implement if presenter logic is needed
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }
}
