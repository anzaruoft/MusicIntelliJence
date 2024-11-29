package use_case.feed;

/*

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

    }

    @Override
    public void switchToChangePasswordView() {
        userPresenter.switchToChangePasswordView();
    }
}
