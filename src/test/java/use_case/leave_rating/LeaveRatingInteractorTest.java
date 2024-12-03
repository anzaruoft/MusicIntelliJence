package use_case.leave_rating;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import entity.User;
import entity.CommonUserFactory;

/**
 * Tests for LeaveRatingInteractor. Testing to see if ratings/posts are left correctly.
 */
public class LeaveRatingInteractorTest {

    @Test
    public void testExecute_AddsRatingToUserPosts() {

        LeaveRatingUserDataAccessInterface mockDataAccess = mock(LeaveRatingUserDataAccessInterface.class);
        LeaveRatingOutputBoundary mockPresenter = mock(LeaveRatingOutputBoundary.class);
        CommonUserFactory userFactory = new CommonUserFactory();

        String username = "testUser";
        String password = "password";
        String email = "test@example.com";
        User testUser = userFactory.create(username, password, email);

        when(mockDataAccess.get(username)).thenReturn(testUser);

        LeaveRatingInputBoundary interactor = new LeaveRatingInteractor(mockDataAccess, mockPresenter);

        String songTitle = "Test Song";
        String rating = "5";
        LeaveRatingInputData inputData = new LeaveRatingInputData(username, songTitle, rating);

        interactor.execute(inputData);

        JSONArray posts = testUser.getPosts();
        assertEquals(1, posts.length());
        assertEquals("Test Song - 5", posts.getString(0));

        verify(mockDataAccess).updateUserPosts(testUser);

        verify(mockPresenter).prepareSuccessView(any());
    }
}
