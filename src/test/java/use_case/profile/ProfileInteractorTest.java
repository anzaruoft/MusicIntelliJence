package use_case.profile;

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

class ProfileInteractorTest {

    @Test
    void successTest() {
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("MichaelYe", "password", "MichaelYe@gmail.com");
        userRepository.save(user);

        ProfileInputData inputData = new ProfileInputData("MichaelYe", user);
        List<String> topSongsList = new ArrayList<>();
        topSongsList.add("Let it Go");
        topSongsList.add("Too Many Nights");
        JSONArray topSongsJson = new JSONArray(topSongsList);
        user.setTopSongs(topSongsJson);
        List<String> friendsList = new ArrayList<>();
        friendsList.add("Jeremy");
        friendsList.add("Kyle");
        JSONArray friendsJson = new JSONArray(friendsList);
        user.setFriends(friendsJson);

        ProfileOutputBoundary successPresenter = new ProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(ProfileOutputData outputData) {
                String username = "MichaelYe";

                List<String> postsList = new ArrayList<>();
                Assertions.assertEquals(friendsList, outputData.getFriends().toList());
                Assertions.assertEquals(postsList, outputData.getPosts());
                Assertions.assertEquals(username, outputData.getUsername());
                Assertions.assertEquals(topSongsList, outputData.getTopSongs().toList());
                System.out.println("Type of getFriends(): " + outputData.getFriends().getClass());
                System.out.println("Contents of getFriends(): " + outputData.getFriends());

            }

            @Override
            public void switchToFeedView() {
                // This is expected
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
        ProfileInputBoundary interactor = new ProfileInteractor(userRepository, successPresenter);
        interactor.execute(inputData);

    }
}
