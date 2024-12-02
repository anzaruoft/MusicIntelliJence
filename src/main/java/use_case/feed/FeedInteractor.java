package use_case.feed;

import org.json.JSONArray;

import entity.User;

/**
 * The Feed Interactor.
 */
public class FeedInteractor implements FeedInputBoundary {

    private final FeedUserDataAccessInterface userDataAccessObject;
    private final FeedOutputBoundary userPresenter;

    public FeedInteractor(FeedUserDataAccessInterface feedDataAccessInterface,
                          FeedOutputBoundary feedOutputBoundary) {
        this.userDataAccessObject = feedDataAccessInterface;
        this.userPresenter = feedOutputBoundary;
    }

    @Override
    public void execute(FeedInputData feedInputData) {
        final String username = feedInputData.getUsername();
        final User user = userDataAccessObject.get(username);
        final JSONArray friendsposts = userDataAccessObject.getFriendsPosts(user.getFriends());
        final FeedOutputData outputData = new FeedOutputData(username, friendsposts);
        userPresenter.prepareSuccessView(outputData);
    }

    @Override
    public void switchToChangePasswordView() {
        userPresenter.switchToChangePasswordView();
    }
}
