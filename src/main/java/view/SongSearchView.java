package view;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import interface_adapter.song_search.SongSearchController;
import interface_adapter.song_search.SongSearchViewModel;

public class SongSearchView extends JPanel implements PropertyChangeListener {
    private final String viewName = "song search";
    private final SongSearchViewModel songSearchViewModel;
    private SongSearchController songSearchController;

    public SongSearchView(SongSearchViewModel songSearchViewModel) {
        this.songSearchViewModel = songSearchViewModel;
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter a song!");

        // Create a text field where the user can type
        JTextField textField = new JTextField(20); // 20 columns wide

        // Create a button to process input
        JButton button = new JButton("Submit");

        this.add(label);
        this.add(textField);
        this.add(button);
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
}

