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
        };
        FriendsInputBoundary interactor = new FriendsInteractor(userRepository, successPresenter);
        interactor.execute(friendsInputData);
    }
}
