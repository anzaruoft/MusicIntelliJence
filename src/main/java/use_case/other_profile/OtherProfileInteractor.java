package use_case.other_profile;

import entity.User;

/**
 * The Other Profile Interactor.
 */
public class OtherProfileInteractor implements OtherProfileInputBoundary {
    private final OtherProfileUserDataAccessInterface userDataAccessObject;
    private final OtherProfileOutputBoundary otherProfilePresenter;

    public OtherProfileInteractor(OtherProfileUserDataAccessInterface userDataAccessInterface,
                                  OtherProfileOutputBoundary otherProfileOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.otherProfilePresenter = otherProfileOutputBoundary;
    }

    @Override
    public void execute(OtherProfileInputData otherProfileInputData) {
        final String otherUsername = otherProfileInputData.getOtherName();
        System.out.println(otherUsername);
        final User user = userDataAccessObject.get(otherUsername);
        final OtherProfileOutputData otherProfileOutputData = new OtherProfileOutputData(user.getName(),
                user.getFriends().length());
        otherProfilePresenter.prepareSuccessView(otherProfileOutputData);
    }

    @Override
    public void switchToFeedView() {
        otherProfilePresenter.switchToFeedView();
    }
}
