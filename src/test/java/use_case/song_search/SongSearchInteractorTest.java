import data_access.SpotifyAPIUserDataAccessObject;
import interface_adapter.song_search.SongSearchViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.SongSearchView;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

class SongSearchInteractorTest {

    private SongSearchView view;
    private SongSearchViewModel mockViewModel;
    private JTextField textField;
    private JTextArea resultsText;
    private JButton submitButton;

    // Mocking the dependencies
    private SpotifyAPIUserDataAccessObject mockSpotifyAPI;

    @BeforeEach
    void setUp() {
        // Mock the ViewModel and Spotify API call
        mockViewModel = mock(SongSearchViewModel.class);
        mockSpotifyAPI = mock(SpotifyAPIUserDataAccessObject.class);

        // Create the view with mocked dependencies
        view = new SongSearchView(mockViewModel);

        // Set up the components needed for the test
        textField = spy(new JTextField(20));  // Use spy to mock behavior of the text field
        resultsText = new JTextArea();
        submitButton = new JButton("Submit");

        // Set the components in the view
        view.add(textField);
        view.add(resultsText);
        view.add(submitButton);
    }

    @Test
    void testSubmitButton_Click_UpdatesResultsText() {
        // Set up a query for the test
        String query = "Test Song";
        String mockSpotifyResponse = "Mock Song Result";  // Mock response from Spotify API

        // Mock the Spotify API response
        when(mockSpotifyAPI.searchSong(query)).thenReturn(mockSpotifyResponse);

        // Set the text field with the search query
        textField.setText(query);

        // Simulate a button click on the "Submit" button
        submitButton.doClick();

        // Verify that the Spotify API call was made
        verify(mockSpotifyAPI).searchSong(query);

        // Verify that the resultsText displays the mocked response
        assertEquals(mockSpotifyResponse, resultsText.getText());

        // Verify that the SongSearchViewModel was updated with the result
        verify(mockViewModel).getState().setSongTitle(mockSpotifyResponse);
    }
}
