package use_case.other_profile;

import entity.User;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OtherProfileInteractorTest {

    private OtherProfileUserDataAccessInterface mockDataAccess;
    private OtherProfileOutputBoundary mockPresenter;
    private OtherProfileInteractor interactor;

    @BeforeEach
    void setUp() {
        mockDataAccess = mock(OtherProfileUserDataAccessInterface.class);
        mockPresenter = mock(OtherProfileOutputBoundary.class);
        interactor = new OtherProfileInteractor(mockDataAccess, mockPresenter);
    }

    @Test
    void testExecute_AlreadyFriends() {
        String thisUsername = "currentUser";
        String otherUsername = "friendUser";

        User thisUser = mock(User.class);
        User otherUser = mock(User.class);

        when(thisUser.getName()).thenReturn(thisUsername);
        when(otherUser.getName()).thenReturn(otherUsername);

        when(mockDataAccess.get(thisUsername)).thenReturn(thisUser);
        when(mockDataAccess.get(otherUsername)).thenReturn(otherUser);

        when(mockDataAccess.isFriends(thisUsername, otherUsername)).thenReturn(true);
        when(mockDataAccess.isFriends(otherUsername, thisUsername)).thenReturn(true);

        OtherProfileInputData inputData = new OtherProfileInputData(thisUsername, otherUsername);

        interactor.execute(inputData);

        ArgumentCaptor<OtherProfileOutputData> captor = ArgumentCaptor.forClass(OtherProfileOutputData.class);
        verify(mockPresenter).prepareSuccessView(captor.capture());

        OtherProfileOutputData outputData = captor.getValue();
        assertEquals(otherUsername, outputData.getThisUsername());
        assertEquals("You are already friends with " + otherUsername, outputData.getResponse());
    }

    @Test
    void testExecute_OneWayFriendship() {
        String thisUsername = "currentUser";
        String otherUsername = "friendUser";

        User thisUser = mock(User.class);
        User otherUser = mock(User.class);

        when(thisUser.getName()).thenReturn(thisUsername);
        when(otherUser.getName()).thenReturn(otherUsername);

        when(mockDataAccess.get(thisUsername)).thenReturn(thisUser);
        when(mockDataAccess.get(otherUsername)).thenReturn(otherUser);

        when(mockDataAccess.isFriends(thisUsername, otherUsername)).thenReturn(true);
        when(mockDataAccess.isFriends(otherUsername, thisUsername)).thenReturn(false);

        OtherProfileInputData inputData = new OtherProfileInputData(thisUsername, otherUsername);

        interactor.execute(inputData);

        ArgumentCaptor<OtherProfileOutputData> captor = ArgumentCaptor.forClass(OtherProfileOutputData.class);
        verify(mockPresenter).prepareSuccessView(captor.capture());

        OtherProfileOutputData outputData = captor.getValue();
        assertEquals(otherUsername, outputData.getThisUsername());
        assertEquals("You have already added " + otherUsername, outputData.getResponse());
    }

    @Test
    void testExecute_AddFriendSuccessfully() {
        String thisUsername = "currentUser";
        String otherUsername = "newUser";

        User thisUser = mock(User.class);
        User otherUser = mock(User.class);

        when(thisUser.getName()).thenReturn(thisUsername);
        when(otherUser.getName()).thenReturn(otherUsername);

        when(mockDataAccess.get(thisUsername)).thenReturn(thisUser);
        when(mockDataAccess.get(otherUsername)).thenReturn(otherUser);

        when(mockDataAccess.isFriends(thisUsername, otherUsername)).thenReturn(false);
        when(mockDataAccess.isFriends(otherUsername, thisUsername)).thenReturn(false);

        JSONArray friends = new JSONArray();
        when(thisUser.getFriends()).thenReturn(friends);

        OtherProfileInputData inputData = new OtherProfileInputData(thisUsername, otherUsername);

        interactor.execute(inputData);

        ArgumentCaptor<OtherProfileOutputData> captor = ArgumentCaptor.forClass(OtherProfileOutputData.class);
        verify(mockPresenter).prepareSuccessView(captor.capture());

        verify(mockDataAccess).updateUserFriends(otherUser, thisUser);
        verify(mockDataAccess).updateUserFriends(thisUser, otherUser);

        OtherProfileOutputData outputData = captor.getValue();
        assertEquals(otherUsername, outputData.getThisUsername());
        assertEquals("You have successfully added " + otherUsername + " as a friend", outputData.getResponse());
    }

    @Test
    void testSwitchToFeedView() {
        interactor.switchToFeedView();
        verify(mockPresenter).switchToFeedView();
    }
}
