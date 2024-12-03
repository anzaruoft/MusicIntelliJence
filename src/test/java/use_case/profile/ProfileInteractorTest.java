package use_case.profile;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProfileInteractorTest {

    @Test
    void successTest() {
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("MichaelYe", "password", "MichaelYe@gmail.com");
        userRepository.save(user);

        JSONArray postsList = new JSONArray();
        postsList.put("Let it Go 5/5");
        postsList.put("Too Many Nights");
        user.setPosts(postsList);

        JSONArray friendsList = new JSONArray();
        friendsList.put("Jeremy");
        friendsList.put("Kyle");
        user.setFriends(friendsList);

        ProfileOutputBoundary successPresenter = new ProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(ProfileOutputData outputData) {
                Assertions.assertEquals("MichaelYe", outputData.getUsername());
                Assertions.assertEquals(postsList.toString(), outputData.getPosts().toString());
                Assertions.assertEquals(friendsList.toString(), outputData.getFriends().toString());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.fail("This should not be called in success scenario.");
            }

            @Override
            public void switchToFeedView() {
                // Expected
            }

            @Override
            public void switchToFriendsView() {
                // Expected
            }
        };

        ProfileInputData inputData = new ProfileInputData("MichaelYe", user);
        ProfileInputBoundary interactor = new ProfileInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void userWithEmptyFriendsAndPostsTest() {
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("EmptyUser", "password", "empty@example.com");
        userRepository.save(user);

        ProfileOutputBoundary successPresenter = new ProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(ProfileOutputData outputData) {
                Assertions.assertEquals("EmptyUser", outputData.getUsername());
                Assertions.assertTrue(outputData.getPosts().isEmpty());
                Assertions.assertTrue(outputData.getFriends().isEmpty());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.fail("This should not be called for an existing user.");
            }

            @Override
            public void switchToFeedView() {
                // Expected
            }

            @Override
            public void switchToFriendsView() {
                // Expected
            }

            @Override
            public void switchToFriendsView() {
                // Provide a dummy implementation for the test
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // Dummy implementation for the test
                Assertions.fail("Unexpected call to prepareFailView: " + errorMessage);
            }
        };

        ProfileInputData inputData = new ProfileInputData("EmptyUser", user);
        ProfileInputBoundary interactor = new ProfileInteractor(userRepository, successPresenter);
        interactor.execute(inputData);

    }

    @Test
    void getUserTest() {
        // Arrange
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("TestUser", "password", "testuser@example.com");

        // Create ProfileInputData with the user object
        ProfileInputData inputData = new ProfileInputData("TestUser", user);

        // Act & Assert
        Assertions.assertEquals(user, inputData.getUser(), "The user should match the one passed to ProfileInputData.");
    }

    @Test
    void switchToFeedViewTest() {
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        ProfileOutputBoundary feedViewPresenter = new ProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(ProfileOutputData outputData) {
                Assertions.fail("Not relevant for this test.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.fail("Not relevant for this test.");
            }

            @Override
            public void switchToFeedView() {
                Assertions.assertTrue(true, "Switch to feed view called successfully.");
            }

            @Override
            public void switchToFriendsView() {
                Assertions.fail("Switch to friends view should not be called.");
            }
        };

        ProfileInputBoundary interactor = new ProfileInteractor(userRepository, feedViewPresenter);
        interactor.switchToFeedView();
    }

    @Test
    void switchToFriendsViewTest() {
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        ProfileOutputBoundary friendsViewPresenter = new ProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(ProfileOutputData outputData) {
                Assertions.fail("Not relevant for this test.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.fail("Not relevant for this test.");
            }

            @Override
            public void switchToFeedView() {
                Assertions.fail("Switch to feed view should not be called.");
            }

            @Override
            public void switchToFriendsView() {
                Assertions.assertTrue(true, "Switch to friends view called successfully.");
            }
        };

        ProfileInputBoundary interactor = new ProfileInteractor(userRepository, friendsViewPresenter);
        interactor.switchToFriendsView();
    }
}
