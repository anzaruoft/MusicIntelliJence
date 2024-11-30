package use_case.other_profile;

import entity.User;
import use_case.other_profile.OtherProfileInputData;
import use_case.other_profile.OtherProfileOutputBoundary;
import use_case.other_profile.OtherProfileOutputData;
import use_case.other_profile.OtherProfileUserDataAccessInterface;

/**
 * The Other Profile interactor.
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
                user.getFriends(),
                user.getPosts(),
                user.getTopSongs());
        otherProfilePresenter.prepareSuccessView(otherProfileOutputData);
    }

    @Override
    public void switchToFeedView() {
        otherProfilePresenter.switchToFeedView();
    }
}