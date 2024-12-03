package use_case.friends;

import data_access.DBUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FriendsInteractorTest {

    @Test
    void successTest() {
        FriendsInputData friendsInputData = new FriendsInputData("Michael");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("MichaelYe", "password", "MichaelYe@gmail.com");
        userRepository.save(user);
        JSONArray friendsList = new JSONArray();
        friendsList.put("Jeremy");
        friendsList.put("Kyle");
        user.setFriends(friendsList);

        FriendsOutputBoundary successPresenter = new FriendsOutputBoundary() {
            @Override
            public void prepareSuccessView(FriendsOutputData outputData) {

                Assertions.assertEquals(friendsList, outputData.getFriendsNames());
            }

            @Override
            public void backToProfileView() {
                // This is expected
            }

            @Override
            public void switchToFriendView(String friendUsername) {
                // Expected
            }
        };
        FriendsInputBoundary interactor = new FriendsInteractor(userRepository, successPresenter);
        interactor.execute(friendsInputData);
    }
    @Test
    void testValidFriendsList() {
        JSONArray friendsList = new JSONArray();
        friendsList.put("Alice");
        friendsList.put("Bob");

        FriendsOutputData outputData = new FriendsOutputData(friendsList);

        Assertions.assertEquals(friendsList, outputData.getFriendsNames());
    }

    @Test
    void testEmptyFriendsList() {
        JSONArray friendsList = new JSONArray();

        FriendsOutputData outputData = new FriendsOutputData(friendsList);

        Assertions.assertEquals(friendsList, outputData.getFriendsNames());
        Assertions.assertTrue(outputData.getFriendsNames().isEmpty());
    }

    @Test
    void testNullFriendsList() {
        FriendsOutputData outputData = new FriendsOutputData(null);

        Assertions.assertNull(outputData.getFriendsNames());
    }

    @Test
    void testDifferentContents() {
        JSONArray friendsList = new JSONArray();
        friendsList.put("Charlie");

        FriendsOutputData outputData = new FriendsOutputData(friendsList);

        JSONArray differentList = new JSONArray();
        differentList.put("Dave");

        Assertions.assertNotEquals(differentList, outputData.getFriendsNames());
    }
}
