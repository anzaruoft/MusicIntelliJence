package use_case.feed;

import org.json.JSONArray;

import entity.User;

import data_access.InMemoryUserDataAccessObject;

/**
 * This is the FeedInteractor.
 */
public class FeedInteractor implements FeedInputBoundary {

    private final FeedUserDataAccessInterface userDataAccessObject;
    private final FeedOutputBoundary userPresenter;
    // Added
    private InMemoryUserDataAccessObject inMemoryUserDataAccessObject;

    public FeedInteractor(FeedUserDataAccessInterface feedDataAccessInterface,
                          FeedOutputBoundary feedOutputBoundary) {
        this.userDataAccessObject = feedDataAccessInterface;
        this.userPresenter = feedOutputBoundary;
    }

    @Override
    public void execute(FeedInputData feedInputData) {
        final String username = feedInputData.getUsername();
        // Added
        inMemoryUserDataAccessObject = new InMemoryUserDataAccessObject();
        inMemoryUserDataAccessObject.setCurrentUsername(username);
        System.out.println(inMemoryUserDataAccessObject.getCurrentUsername());

        final User user = userDataAccessObject.get(username);
        // Remove Later
        System.out.println("Feed Interactor" + user.getName());

        final JSONArray friendsposts = userDataAccessObject.getFriendsPosts(user.getFriends());
        final FeedOutputData outputData = new FeedOutputData(username, friendsposts, user,
                inMemoryUserDataAccessObject);
        userDataAccessObject.updateUserPosts(user);
        userPresenter.prepareSuccessView(outputData);
    }

    @Override
    public void switchToChangePasswordView() {
        userPresenter.switchToChangePasswordView();
    }
}
