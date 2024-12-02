package use_case.song_search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SongSearchInteractorTest {
    private SongSearchUserDataAccessInterface mockDataAccess;
    private SongSearchOutputBoundary mockPresenter;
    private SongSearchInteractor interactor;

    @BeforeEach
    void setUp() {
        mockDataAccess = mock(SongSearchUserDataAccessInterface.class);
        mockPresenter = mock(SongSearchOutputBoundary.class);
        interactor = new SongSearchInteractor(mockDataAccess, mockPresenter);
    }

    @Test
    void testSwitchToFeedView() {
        // Call the method
        interactor.switchToFeedView();

        // Verify that the presenter method was called
        verify(mockPresenter).switchToFeedView();
    }

    @Test
    void testSwitchToLeaveRatingView() {
        // Call the method
        interactor.switchToLeaveRatingView();

        // Verify that the presenter method was called
        verify(mockPresenter).switchToLeaveRatingView();
    }
}

