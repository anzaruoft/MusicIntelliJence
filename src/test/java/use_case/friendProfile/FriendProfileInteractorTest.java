package use_case.friendProfile;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FriendProfileInteractorTest {

    @Test
    void successTest() {
        FriendProfileInputData friendProfileInputData = new FriendProfileInputData("Jeremy");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Jeremy", "password", "Jeremy452@gmail.com");
        userRepository.save(user);
        JSONArray friendsList = new JSONArray();
        friendsList.put("Michael Ye");
        friendsList.put("Kyle");
        user.setFriends(friendsList);

        FriendProfileOutputBoundary successPresenter = new FriendProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(FriendProfileOutputData outputData) {
                JSONArray emptyPosts = new JSONArray();
                Assertions.assertEquals("Jeremy", outputData.getUsername());
                Assertions.assertEquals(friendsList.toString(), outputData.getFriends().toString());
                Assertions.assertEquals(emptyPosts.toString(), outputData.getPosts().toString());
            }

            @Override
            public void switchToFriendsView() {
                // This is expected
            }
        };
        FriendProfileInputBoundary interactor = new FriendProfileInteractor(userRepository, successPresenter);
        interactor.execute(friendProfileInputData);
    }

    @Test
    void userNotFoundTest() {
        FriendProfileInputData friendProfileInputData = new FriendProfileInputData("NonExistentUser");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        FriendProfileOutputBoundary notFoundPresenter = new FriendProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(FriendProfileOutputData outputData) {
                Assertions.fail("Should not be called when user does not exist");
            }

            @Override
            public void switchToFriendsView() {
                Assertions.fail("Should not switch views when user does not exist");
            }
        };

        FriendProfileInputBoundary interactor = new FriendProfileInteractor(userRepository, notFoundPresenter);
        interactor.execute(friendProfileInputData);
    }

    @Test
    void userWithPostsTest() {
        FriendProfileInputData friendProfileInputData = new FriendProfileInputData("Kyle");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Kyle", "password", "kyle@example.com");
        JSONArray posts = new JSONArray();
        posts.put("Post 1");
        posts.put("Post 2");
        user.setPosts(posts);
        userRepository.save(user);

        FriendProfileOutputBoundary successPresenter = new FriendProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(FriendProfileOutputData outputData) {
                Assertions.assertEquals("Kyle", outputData.getUsername());
                Assertions.assertEquals(posts.toString(), outputData.getPosts().toString());
                Assertions.assertTrue(outputData.getFriends().isEmpty());
            }

            @Override
            public void switchToFriendsView() {
                // Expected behavior
            }
        };

        FriendProfileInputBoundary interactor = new FriendProfileInteractor(userRepository, successPresenter);
        interactor.execute(friendProfileInputData);
    }

    @Test
    void userWithTopSongsTest() {
        FriendProfileInputData friendProfileInputData = new FriendProfileInputData("Michael Ye");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Michael Ye", "password", "michael@example.com");
        JSONArray topSongs = new JSONArray();
        topSongs.put("Song A");
        topSongs.put("Song B");
        user.setTopSongs(topSongs);
        userRepository.save(user);

        FriendProfileOutputBoundary successPresenter = new FriendProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(FriendProfileOutputData outputData) {
                Assertions.assertEquals("Michael Ye", outputData.getUsername());
                Assertions.assertTrue(outputData.getPosts().isEmpty());
                Assertions.assertTrue(outputData.getFriends().isEmpty());
            }

            @Override
            public void switchToFriendsView() {
                // Expected behavior
            }
        };

        FriendProfileInputBoundary interactor = new FriendProfileInteractor(userRepository, successPresenter);
        interactor.execute(friendProfileInputData);
    }

    @Test
    void switchToFriendsViewTest() {
        FriendProfileInputData friendProfileInputData = new FriendProfileInputData("Jeremy");
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        FriendProfileOutputBoundary successPresenter = new FriendProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(FriendProfileOutputData outputData) {
                Assertions.assertEquals("Jeremy", outputData.getUsername());
            }

            @Override
            public void switchToFriendsView() {
                Assertions.assertTrue(true);
            }
        };

        FriendProfileInputBoundary interactor = new FriendProfileInteractor(userRepository, successPresenter);
        interactor.switchToFriendsView();
    }
}
