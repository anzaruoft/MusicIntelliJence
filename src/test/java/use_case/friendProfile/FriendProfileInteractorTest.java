package use_case.friendProfile;

import data_access.DBUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FriendProfileInteractorTest {

    @Test
    void successTest() {
        FriendProfileInputData friendProfileInputData = new FriendProfileInputData("Jeremy");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Jeremy", "password", "Jeremy452@gmail.com");
        userRepository.save(user);
        List<String> friendsList = new ArrayList<>();
        friendsList.add("MichaelYe");
        friendsList.add("Kyle");
        user.setFriends(friendsList);

        FriendProfileOutputBoundary successPresenter = new FriendProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(FriendProfileOutputData outputData) {
                List<String> emptyPosts = new ArrayList<>();
                List<String> emptySongs = new ArrayList<>();
                Assertions.assertEquals("Jeremy", outputData.getUsername());
                Assertions.assertEquals(friendsList, outputData.getFriends());
                Assertions.assertEquals(emptyPosts, outputData.getPosts());
                Assertions.assertEquals(emptySongs, outputData.getTopSongs());
            }

            @Override
            public void switchToFriendsView() {
                // This is expected
            }
        };
        FriendProfileInputBoundary interactor = new FriendProfileInteractor(userRepository, successPresenter);
        interactor.execute(friendProfileInputData);
    }
}
