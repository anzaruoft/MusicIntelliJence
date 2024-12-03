package use_case.feed;


import entity.User;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

/**
 * Testing for the FeedInteractor.
 */

public class FeedInteractorTest {
    @Test
    public void testSwitchToChangePasswordView() {
        // Arrange
        FeedUserDataAccessInterface mockDataAccess = mock(FeedUserDataAccessInterface.class);
        FeedOutputBoundary mockPresenter = mock(FeedOutputBoundary.class);

        FeedInteractor interactor = new FeedInteractor(mockDataAccess, mockPresenter);

        // Act
        interactor.switchToChangePasswordView();

        // Assert
        verify(mockPresenter).switchToChangePasswordView();
    }

    @Test
    public void testSwitchToProfileSearchView() {
        // Arrange
        FeedUserDataAccessInterface mockDataAccess = mock(FeedUserDataAccessInterface.class);
        FeedOutputBoundary mockPresenter = mock(FeedOutputBoundary.class);

        FeedInteractor interactor = new FeedInteractor(mockDataAccess, mockPresenter);

        String username = "testUser";
        // Act
        interactor.switchToProfileSearchView(username);

        // Assert
        verify(mockPresenter).switchToProfileSearchView(username);
    }

    @Test
    public void testSwitchToProfileView() {
        FeedUserDataAccessInterface mockDataAccess = mock(FeedUserDataAccessInterface.class);
        FeedOutputBoundary mockPresenter = mock(FeedOutputBoundary.class);
        FeedInteractor interactor = new FeedInteractor(mockDataAccess, mockPresenter);
        String username = "testUser";
        interactor.switchToProfileView(username);
        verify(mockPresenter).switchToProfileView(username);
    }

    @Test
    public void testSwitchToSongSearchView() {
        FeedUserDataAccessInterface mockDataAccess = mock(FeedUserDataAccessInterface.class);
        FeedOutputBoundary mockPresenter = mock(FeedOutputBoundary.class);
        FeedInteractor interactor = new FeedInteractor(mockDataAccess, mockPresenter);
        String username = "testUser";
        interactor.switchToSongSearchView(username);
    }

    @Test
    public void testExecuteWithValidUser() {
        // Arrange
        FeedUserDataAccessInterface mockDataAccess = mock(FeedUserDataAccessInterface.class);
        FeedOutputBoundary mockPresenter = mock(FeedOutputBoundary.class);

        FeedInteractor interactor = new FeedInteractor(mockDataAccess, mockPresenter);

        String username = "testUser";
        User mockUser = mock(User.class);
        JSONArray mockFriendPosts = new JSONArray("[{\"post\": \"Friend post\"}]");

        when(mockDataAccess.get(username)).thenReturn(mockUser);
        when(mockDataAccess.getFriendsPosts(mockUser.getFriends())).thenReturn(mockFriendPosts);

        FeedInputData inputData = new FeedInputData(username);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockDataAccess).get(username);
        verify(mockDataAccess).getFriendsPosts(mockUser.getFriends());
        verify(mockPresenter).prepareSuccessView(any(FeedOutputData.class));
    }

    public void testExecuteWithNonExistentUser() {
        // Arrange
        FeedUserDataAccessInterface mockDataAccess = mock(FeedUserDataAccessInterface.class);
        FeedOutputBoundary mockPresenter = mock(FeedOutputBoundary.class);

        FeedInteractor interactor = new FeedInteractor(mockDataAccess, mockPresenter);

        String username = "unknownUser";

        when(mockDataAccess.get(username)).thenReturn(null);

        FeedInputData inputData = new FeedInputData(username);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockPresenter).prepareFailView("User not found.");
    }

    @Test
    public void testExecuteWithNoFriends() {
        // Arrange
        FeedUserDataAccessInterface mockDataAccess = mock(FeedUserDataAccessInterface.class);
        FeedOutputBoundary mockPresenter = mock(FeedOutputBoundary.class);

        FeedInteractor interactor = new FeedInteractor(mockDataAccess, mockPresenter);

        String username = "lonelyUser";
        User mockUser = mock(User.class);
        JSONArray emptyFriendsPosts = new JSONArray("[]");

        when(mockDataAccess.get(username)).thenReturn(mockUser);
        when(mockDataAccess.getFriendsPosts(mockUser.getFriends())).thenReturn(emptyFriendsPosts);

        FeedInputData inputData = new FeedInputData(username);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockDataAccess).get(username);
        verify(mockDataAccess).getFriendsPosts(mockUser.getFriends());
        verify(mockPresenter).prepareSuccessView(any(FeedOutputData.class));
    }

}


