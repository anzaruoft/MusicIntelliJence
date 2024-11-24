package use_case.profile;

import data_access.DBUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ProfileInteractorTest {

    @Test
    void successTest() {
        ProfileInputData inputData = new ProfileInputData("MichaelYe");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("MichaelYe", "password", "MichaelYe@gmail.com");
        userRepository.save(user);
        List<String> topSongsList = new ArrayList<>();
        topSongsList.add("Let it Go");
        topSongsList.add("Too Many Nights");
        user.setTopSongs(topSongsList);
        List<String> friendsList = new ArrayList<>();
        friendsList.add("Jeremy");
        friendsList.add("Kyle");
        user.setFriends(friendsList);

        ProfileOutputBoundary successPresenter = new ProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(ProfileOutputData outputData) {
                String username = "MichaelYe";

                List<String> postsList = new ArrayList<>();
                Assertions.assertEquals(friendsList, outputData.getFriends());
                Assertions.assertEquals(postsList, outputData.getPosts());
                Assertions.assertEquals(username, outputData.getUsername());
                Assertions.assertEquals(topSongsList, outputData.getTopSongs());

            }

            @Override
            public void switchToFeedView() {
                // This is expected
            }
        };
        ProfileInputBoundary interactor = new ProfileInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
