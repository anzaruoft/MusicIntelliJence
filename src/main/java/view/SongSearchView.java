package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.feed.FeedState;
import interface_adapter.feed.FeedViewModel;
import interface_adapter.song_search.SongSearchController;
import interface_adapter.song_search.SongSearchPresenter;
import interface_adapter.song_search.SongSearchState;
import interface_adapter.song_search.SongSearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SongSearchView extends JPanel implements PropertyChangeListener, ActionListener {
    private final String viewName = "song search";
    private final SongSearchViewModel songSearchViewModel;
    private SongSearchPresenter songSearchPresenter;
    private SongSearchController songSearchController;
    private FeedViewModel feedViewModel;
    private ViewManagerModel viewManagerModel;

    public SongSearchView(SongSearchViewModel songSearchViewModel) {
        this.songSearchViewModel = songSearchViewModel;
        this.songSearchViewModel.addPropertyChangeListener(this);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter a song!");

        // Create a text field where the user can type
        JTextField textField = new JTextField(20); // 20 columns wide

        // Create a button to process input
        JButton submitButton = new JButton("Submit");
        final JButton backButton = new JButton("Back");

        this.add(label);
        this.add(textField);
        this.add(submitButton);
        this.add(backButton);

        this.setBackground(Color.PINK);

        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(backButton)) {
                            final SongSearchState currentState = songSearchViewModel.getState();
                            songSearchPresenter = new SongSearchPresenter(viewManagerModel,
                                    songSearchViewModel, feedViewModel);
                            songSearchPresenter.switchToFeedView(
                                    currentState.getUsername()
                            );
                        }
//                        SongSearchView songSearchView = new SongSearchView(songSearchViewModel);
//                        songSearchView.setSongSearchController(songSearchController);
//                        songSearchController.switchToFeedView();
                    }
                }
        );

        submitButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        songSearchController.switchToLeaveRatingView();
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
}
