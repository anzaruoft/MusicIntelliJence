package use_case.song_search;

import data_access.SpotifyAPIUserDataAccessObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Testing for the SongSearchInteractor.
 */
public class SongSearchInteractorTest {

    @Test
    public void testSearchSongWithValidQuery() {
        try {
            String query = "Imagine";

            String result = SpotifyAPIUserDataAccessObject.searchSong(query);

            assertNotNull(result, "Result should not be null");
            assertFalse(result.isEmpty(), "Result should not be empty");
            System.out.println("Search result: " + result); // Optional: Log the result

        } catch (Exception e) {
            fail("API call failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testSwitchToFeedView() {
        // Arrange: Mock dependencies
        SongSearchUserDataAccessInterface mockDataAccess = mock(SongSearchUserDataAccessInterface.class);
        SongSearchOutputBoundary mockPresenter = mock(SongSearchOutputBoundary.class);

        SongSearchInteractor interactor = new SongSearchInteractor(mockDataAccess, mockPresenter);

        interactor.switchToFeedView();

        verify(mockPresenter).switchToFeedView();
    }

    @Test
    public void testSwitchToLeaveRatingView() {

        SongSearchUserDataAccessInterface mockDataAccess = mock(SongSearchUserDataAccessInterface.class);
        SongSearchOutputBoundary mockPresenter = mock(SongSearchOutputBoundary.class);

        SongSearchInteractor interactor = new SongSearchInteractor(mockDataAccess, mockPresenter);

        interactor.switchToLeaveRatingView();

        verify(mockPresenter).switchToLeaveRatingView();
    }
}

