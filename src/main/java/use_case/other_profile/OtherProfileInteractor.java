package use_case.other_profile;

import entity.User;

import data_access.InMemoryUserDataAccessObject;
import org.json.JSONArray;

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

    // Adds friend
    @Override
    public void execute(OtherProfileInputData otherProfileInputData) {
        final String otherUsername = otherProfileInputData.getOtherName();
        final String thisUsername = otherProfileInputData.getThisName();
        // Flipped but correct vv
        final User thisUser = userDataAccessObject.get(otherUsername);
        final User otherUser = userDataAccessObject.get(thisUsername);

        final JSONArray friends = thisUser.getFriends();

        final String response;

        // Checks if the thisUser is in the other User's friends data
        if (userDataAccessObject.isFriends(thisUser.getName(), otherUser.getName())) {
            System.out.println(thisUser.getName() + " is in " + otherUser.getName() + "'s friends");
            // Checks if the otherUser is in the thisUser's friends data
            if (userDataAccessObject.isFriends(otherUser.getName(), thisUser.getName())) {
                System.out.println("You are already friends with " + otherUser.getName());
                response = "You are already friends with " + otherUser.getName();
            }
            else {
                response = "You have already added " + otherUser.getName();
            }
        }
        // Neither users are friends
        else {
            userDataAccessObject.updateUserFriends(otherUser, thisUser);
            response = "You have successfully added " + otherUser.getName() + " as a friend";

            friends.put(otherUser.getName());
            System.out.println("put called");
            thisUser.setFriends(friends);

            userDataAccessObject.updateUserFriends(thisUser, otherUser);
        }
        final OtherProfileOutputData otherProfileOutputData = new OtherProfileOutputData(thisUsername, response);
        otherProfilePresenter.prepareSuccessView(otherProfileOutputData);
    }

    @Override
    public void switchToFeedView() {
        otherProfilePresenter.switchToFeedView();
    }
}
