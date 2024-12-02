package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import data_access.SpotifyAPIUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.leave_rating.LeaveRatingController;
import interface_adapter.song_search.SongSearchController;
import interface_adapter.song_search.SongSearchPresenter;
import interface_adapter.song_search.SongSearchViewModel;

/**
 * This is the SongSearchView class.
 */
public class SongSearchView extends JPanel implements PropertyChangeListener, ActionListener {
    private final String viewName = "song search";
    private final SongSearchViewModel songSearchViewModel;
    private SongSearchPresenter songSearchPresenter;
    private SongSearchController songSearchController;
    private FeedViewModel feedViewModel;
    private ViewManagerModel viewManagerModel;
    private LeaveRatingController leaveRatingController;
    private JTextField ratingField;

    public SongSearchView(SongSearchViewModel songSearchViewModel) {
        this.songSearchViewModel = songSearchViewModel;
        this.songSearchViewModel.addPropertyChangeListener(this);

        final JPanel panel = new JPanel();
        final JLabel label = new JLabel("Enter a song!");

        // Create a text field where the user can type
        final JTextField textField = new JTextField(20);

        // Create a button to process input
        final JButton submitButton = new JButton("Submit");
        final JButton backButton = new JButton("Back");
        final JButton rateButton = new JButton("Rate");

        // Results Textbox
        final JTextArea resultsText = new JTextArea();
        resultsText.setBounds(10, 100, 350, 150);
        resultsText.setLineWrap(true);
        resultsText.setWrapStyleWord(true);
        panel.add(resultsText);
        resultsText.setEditable(false);

        // Place to leave a rating
        final JLabel ratinglabel = new JLabel("Leave a Rating (1-5):");
        ratingField = new JTextField(5);

        this.add(label);
        this.add(textField);
        this.add(submitButton);
        this.add(backButton);
        this.add(rateButton);
        this.add(resultsText);
        this.add(ratinglabel);
        this.add(ratingField);

        this.setBackground(Color.PINK);

        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        songSearchController.switchToFeedView();
                    }
                }
        );

        rateButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String rating = ratingField.getText();
                        try {
                            songSearchViewModel.getState().setRating(rating);
                        }
                        catch (Exception ex) {
                            resultsText.setText("Error: " + ex.getMessage());
                        }
                        songSearchController.switchToLeaveRatingView();
                    }
                }
        );

        // ACTUALLY... OVER HERE WE SHOULD BE SWITCHING TO THE SPOTIFY API RESULT
        submitButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String query = textField.getText();
                        try {
                            String result = SpotifyAPIUserDataAccessObject.searchSong(query);
                            songSearchViewModel.getState().setSongTitle(result);
                            resultsText.setText(result);
                        }
                        catch (Exception ex) {
                            resultsText.setText("Error: " + ex.getMessage());
                        }

                    }
                }
        );
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    public String getViewName() {
        return viewName;
    }

    public void setSongSearchController(SongSearchController songSearchController) {
        this.songSearchController = songSearchController;
    }

    public void setSongSearchPresenter(SongSearchPresenter songSearchPresenter) {
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    public void setLeaveRatingController(LeaveRatingController leaveRatingController) {
        this.leaveRatingController = leaveRatingController;

    }
}
