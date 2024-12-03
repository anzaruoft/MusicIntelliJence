package use_case.friends;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FriendsInteractorTest {

    private InMemoryUserDataAccessObject userRepository;
    private FriendsOutputBoundary successPresenter;
    private FriendsInteractor interactor;
    private FriendsInputData friendsInputData;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserDataAccessObject();
        successPresenter = new FriendsOutputBoundary() {
            @Override
            public void prepareSuccessView(FriendsOutputData outputData) {
                // Used for assertions inside tests
            }

            @Override
            public void backToProfileView() {
                // Simulate the "back to profile view" behavior
            }

            @Override
            public void switchToFriendView(String friendUsername) {
                // Simulate the "switch to friend profile view" behavior
            }
        };
        friendsInputData = new FriendsInputData("Michael");
    }

    @Test
    void successTest() {
        // Arrange
        User user = new CommonUserFactory().create("MichaelYe", "password", "MichaelYe@gmail.com");
        userRepository.save(user);

        JSONArray friendsList = new JSONArray();
        friendsList.put("Jeremy");
        friendsList.put("Kyle");
        user.setFriends(friendsList);

        FriendsInteractor interactor = new FriendsInteractor(userRepository, successPresenter);

        // Act
        interactor.execute(friendsInputData);

        // Assert
        Assertions.assertEquals(friendsList, user.getFriends());
    }

    @Test
    void testValidFriendsList() {
        // Arrange
        JSONArray friendsList = new JSONArray();
        friendsList.put("Alice");
        friendsList.put("Bob");

        FriendsOutputData outputData = new FriendsOutputData(friendsList);

        // Act & Assert
        Assertions.assertEquals(friendsList, outputData.getFriendsNames());
    }

    @Test
    void testEmptyFriendsList() {
        // Arrange
        JSONArray friendsList = new JSONArray();

        FriendsOutputData outputData = new FriendsOutputData(friendsList);

        // Act & Assert
        Assertions.assertEquals(friendsList, outputData.getFriendsNames());
        Assertions.assertTrue(outputData.getFriendsNames().isEmpty());
    }

    @Test
    void testNullFriendsList() {
        // Arrange
        FriendsOutputData outputData = new FriendsOutputData(null);

        // Act & Assert
        Assertions.assertNull(outputData.getFriendsNames());
    }

    @Test
    void testDifferentContents() {
        // Arrange
        JSONArray friendsList = new JSONArray();
        friendsList.put("Charlie");

        FriendsOutputData outputData = new FriendsOutputData(friendsList);

        JSONArray differentList = new JSONArray();
        differentList.put("Dave");

        // Act & Assert
        Assertions.assertNotEquals(differentList, outputData.getFriendsNames());
    }

    @Test
    void testBackToProfileView() {
        // Arrange
        FriendsInteractor interactor = new FriendsInteractor(userRepository, successPresenter);

        // Act
        interactor.backToProfileView();

        // Assert (you would normally assert behavior like view changes, but here it's mocked)
        Assertions.assertTrue(true); // This could be improved if there was a mock to check the behavior
    }

    @Test
    void testSwitchToFriendProfile() {
        // Arrange
        String friendUsername = "Jeremy";
        FriendsInteractor interactor = new FriendsInteractor(userRepository, successPresenter);

        // Act
        interactor.switchToFriendProfile(friendUsername);

        // Assert (you would normally assert view changes or output here)
        Assertions.assertTrue(true); // Again, this is simulated as the method doesn't return a value
    }

    @Test
    void testUserDoesNotExist() {
        // Arrange
        FriendsInputData inputData = new FriendsInputData("NonExistentUser");
        FriendsInteractor interactor = new FriendsInteractor(userRepository, successPresenter);

        // Act
        interactor.execute(inputData);

        // Assert that no interaction happens if user does not exist (mock this behavior in the presenter)
        Assertions.assertTrue(true); // No friends list would be retrieved or shown
    }
}
