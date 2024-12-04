package use_case.profile_search;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProfileSearchInteractorTest {

    private ProfileSearchUserDataAccessInterface mockDataAccess;
    private ProfileSearchOutputBoundary mockPresenter;
    private ProfileSearchInteractor interactor;

    @BeforeEach
    void setUp() {
        mockDataAccess = mock(ProfileSearchUserDataAccessInterface.class);
        mockPresenter = mock(ProfileSearchOutputBoundary.class);
        interactor = new ProfileSearchInteractor(mockDataAccess, mockPresenter);
    }

    @Test
    void testExecute_AccountDoesNotExist() {
        String inputUsername = "nonexistentUser";
        String thisUsername = "currentUser";

        ProfileSearchInputData inputData = new ProfileSearchInputData(inputUsername, thisUsername);

        when(mockDataAccess.existsByName(inputUsername)).thenReturn(false);

        interactor.execute(inputData);

        verify(mockPresenter).prepareFailView(inputUsername + ": Account does not exist.");
    }

    @Test
    void testExecute_SearchOwnAccount() {
        String username = "currentUser";

        ProfileSearchInputData inputData = new ProfileSearchInputData(username, username);

        when(mockDataAccess.existsByName(username)).thenReturn(true);

        interactor.execute(inputData);

        verify(mockPresenter).prepareFailView(username + ": is your account.");
    }

    @Test
    void testExecute_ValidSearch() {
        String inputUsername = "friendUser";
        String thisUsername = "currentUser";

        User thisUser = mock(User.class);
        User inputUser = mock(User.class);

        when(mockDataAccess.existsByName(inputUsername)).thenReturn(true);
        when(mockDataAccess.get(thisUsername)).thenReturn(thisUser);
        when(mockDataAccess.get(inputUsername)).thenReturn(inputUser);

        when(thisUser.getName()).thenReturn(thisUsername);
        when(inputUser.getName()).thenReturn(inputUsername);
//        when(inputUser.getFriends().length()).thenReturn(5);

        ProfileSearchInputData inputData = new ProfileSearchInputData(inputUsername, thisUsername);

        interactor.execute(inputData);

        ArgumentCaptor<ProfileSearchOutputData> captor = ArgumentCaptor.forClass(ProfileSearchOutputData.class);
        verify(mockPresenter).prepareSuccessView(captor.capture());

        ProfileSearchOutputData outputData = captor.getValue();
//        assertEquals(5, outputData.getFriendCount());
        assertEquals(inputUsername, outputData.getSearchedUserName());
        assertEquals(thisUsername, outputData.getThisUserName());
    }

    @Test
    void testSwitchToFeedView() {
        interactor.switchToFeedView();
        verify(mockPresenter).switchToFeedView();
    }
}
