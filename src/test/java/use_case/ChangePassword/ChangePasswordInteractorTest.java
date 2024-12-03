package use_case.ChangePassword;

import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.change_password.*;
import use_case.song_search.SongSearchInteractor;
import use_case.song_search.SongSearchOutputBoundary;
import use_case.song_search.SongSearchUserDataAccessInterface;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ChangePasswordInteractorTest {

    @Test
    public void testChangePasswordSuccess() {
        // Arrange
        ChangePasswordUserDataAccessInterface mockDataAccess = mock(ChangePasswordUserDataAccessInterface.class);
        ChangePasswordOutputBoundary mockPresenter = mock(ChangePasswordOutputBoundary.class);
        UserFactory userFactory = new CommonUserFactory();

        ChangePasswordInteractor interactor = new ChangePasswordInteractor(mockDataAccess, mockPresenter, userFactory);

        ChangePasswordInputData inputData = new ChangePasswordInputData("testUser", "newPassword", "testUser@gmail.com");

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockDataAccess).changePassword(any(User.class));
        verify(mockPresenter).prepareSuccessView(any(ChangePasswordOutputData.class));
    }

    @Test
    public void testChangePasswordFailureEmptyPassword() {
        ChangePasswordUserDataAccessInterface mockDataAccess = mock(ChangePasswordUserDataAccessInterface.class);
        ChangePasswordOutputBoundary mockPresenter = mock(ChangePasswordOutputBoundary.class);
        UserFactory mockUserFactory = mock(UserFactory.class);

        // Simulate user creation failure (return null)
        when(mockUserFactory.create(anyString(), eq(""), anyString())).thenReturn(null);

        ChangePasswordInteractor interactor = new ChangePasswordInteractor(mockDataAccess, mockPresenter, mockUserFactory);

        ChangePasswordInputData inputData = new ChangePasswordInputData("testUser", "", "testUser@gmail.com");

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockPresenter).prepareFailView("userCreationFailure"); // Updated to match the actual behavior
        verify(mockDataAccess, never()).changePassword(any(User.class));
        verify(mockPresenter, never()).prepareSuccessView(any(ChangePasswordOutputData.class));
    }

    @Test
    public void testSwitchToChangePasswordView() {
        ChangePasswordUserDataAccessInterface mockDataAccess = mock(ChangePasswordUserDataAccessInterface.class);
        ChangePasswordOutputBoundary mockPresenter = mock(ChangePasswordOutputBoundary.class);

        ChangePasswordInteractor interactor = new ChangePasswordInteractor(mockDataAccess, mockPresenter,
                new CommonUserFactory());

        interactor.switchToChangePasswordView();

        verify(mockPresenter).switchToChangePasswordView();
    }

    @Test
    public void testChangePasswordFailureNullPassword() {
        ChangePasswordUserDataAccessInterface mockDataAccess = mock(ChangePasswordUserDataAccessInterface.class);
        ChangePasswordOutputBoundary mockPresenter = mock(ChangePasswordOutputBoundary.class);
        UserFactory mockUserFactory = mock(UserFactory.class);

        // Simulate user creation failure (return null for null password)
        when(mockUserFactory.create(anyString(), eq(null), anyString())).thenReturn(null);

        ChangePasswordInteractor interactor = new ChangePasswordInteractor(mockDataAccess, mockPresenter, mockUserFactory);

        ChangePasswordInputData inputData = new ChangePasswordInputData("testUser", null, "testUser@gmail.com");

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockPresenter).prepareFailView("userCreationFailure"); // Expect "userCreationFailure" due to null user
        verify(mockDataAccess, never()).changePassword(any(User.class));
        verify(mockPresenter, never()).prepareSuccessView(any(ChangePasswordOutputData.class));
    }

    @Test
    public void testChangePasswordFailureUserCreation() {
        // Arrange
        ChangePasswordUserDataAccessInterface mockDataAccess = mock(ChangePasswordUserDataAccessInterface.class);
        ChangePasswordOutputBoundary mockPresenter = mock(ChangePasswordOutputBoundary.class);
        UserFactory mockUserFactory = mock(UserFactory.class);

        // Simulate user creation failure
        when(mockUserFactory.create(anyString(), anyString(), anyString())).thenReturn(null);

        ChangePasswordInteractor interactor = new ChangePasswordInteractor(mockDataAccess, mockPresenter, mockUserFactory);

        ChangePasswordInputData inputData = new ChangePasswordInputData("testUser", "validPassword", "testUser@gmail.com");

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockPresenter).prepareFailView("userCreationFailure");
        verify(mockDataAccess, never()).changePassword(any(User.class));
        verify(mockPresenter, never()).prepareSuccessView(any(ChangePasswordOutputData.class));
    }

    @Test
    public void testChangePasswordFailureEmptyUsername() {
        ChangePasswordUserDataAccessInterface mockDataAccess = mock(ChangePasswordUserDataAccessInterface.class);
        ChangePasswordOutputBoundary mockPresenter = mock(ChangePasswordOutputBoundary.class);
        UserFactory mockUserFactory = mock(UserFactory.class);

        ChangePasswordInteractor interactor = new ChangePasswordInteractor(mockDataAccess, mockPresenter, mockUserFactory);

        ChangePasswordInputData inputData = new ChangePasswordInputData("", "password", "testUser@gmail.com");

        interactor.execute(inputData);

        verify(mockPresenter).prepareFailView("passwordFailureEmpty");
        verify(mockDataAccess, never()).changePassword(any(User.class));
    }

    @Test
    public void testChangePasswordFailureNullUsername() {
        // Arrange
        ChangePasswordUserDataAccessInterface mockDataAccess = mock(ChangePasswordUserDataAccessInterface.class);
        ChangePasswordOutputBoundary mockPresenter = mock(ChangePasswordOutputBoundary.class);
        UserFactory mockUserFactory = mock(UserFactory.class);

        ChangePasswordInteractor interactor = new ChangePasswordInteractor(mockDataAccess, mockPresenter, mockUserFactory);

        ChangePasswordInputData inputData = new ChangePasswordInputData(null, "password", "testUser@gmail.com");

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockPresenter).prepareFailView("passwordFailureEmpty");
        verify(mockDataAccess, never()).changePassword(any(User.class));
    }

    @Test
    public void testChangePasswordFailureNullFactoryOutput() {
        // Arrange
        ChangePasswordUserDataAccessInterface mockDataAccess = mock(ChangePasswordUserDataAccessInterface.class);
        ChangePasswordOutputBoundary mockPresenter = mock(ChangePasswordOutputBoundary.class);
        UserFactory mockUserFactory = mock(UserFactory.class);

        when(mockUserFactory.create(anyString(), eq("validPassword"), anyString())).thenReturn(null);

        ChangePasswordInteractor interactor = new ChangePasswordInteractor(mockDataAccess, mockPresenter, mockUserFactory);

        ChangePasswordInputData inputData = new ChangePasswordInputData("testUser", "validPassword", "email@gmail.com");

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockPresenter).prepareFailView("userCreationFailure");
        verify(mockDataAccess, never()).changePassword(any(User.class));
    }

}

