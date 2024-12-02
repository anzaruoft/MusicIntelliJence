package use_case.leave_rating;

import org.json.JSONArray;

import entity.User;

/**
 * This is the LeaveRatingInteractor class.
 */
public class LeaveRatingInteractor implements LeaveRatingInputBoundary {
    private final LeaveRatingUserDataAccessInterface userDataAccessObject;
    private final LeaveRatingOutputBoundary userPresenter;

    public LeaveRatingInteractor(LeaveRatingUserDataAccessInterface leaveRatingUserDataAccessInterface,
                                 LeaveRatingOutputBoundary leaveRatingOutputBoundary) {
        this.userDataAccessObject = leaveRatingUserDataAccessInterface;
        this.userPresenter = leaveRatingOutputBoundary;
    }

    @Override
    public void execute(LeaveRatingInputData leaveRatingInputData) {
        final String songTitle = leaveRatingInputData.getSongTitle();
        final String rating = leaveRatingInputData.getRating();
        final String username = leaveRatingInputData.getUsername();
        final User user = userDataAccessObject.get(username);
        final LeaveRatingOutputData leaveRatingOutputData = new LeaveRatingOutputData();
        final JSONArray posts = user.getPosts();
        posts.put(songTitle + " " + rating + "/5");
        user.setPosts(posts);

        userDataAccessObject.save(user);
        userPresenter.prepareSuccessView(leaveRatingOutputData);
    }

    @Override
    public void switchToSongSearchView() {
        userPresenter.switchToSongSearchView();
    }

    @Override
    public void switchtoFeedView() {
        userPresenter.switchToFeedView();
    }
}
