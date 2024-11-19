package use_case.signup;

import entity.User;
import entity.UserFactory;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (signupInputData.getUsername().isEmpty() || signupInputData.getPassword().isEmpty()
                || signupInputData.getEmail().isEmpty()) {
            userPresenter.prepareFailView("Username or password or email cannot be empty");
        }

        else if (signupInputData.getPassword().length() < 6) {
            userPresenter.prepareFailView("Password must be at least 6 characters");
            return;
        }
        else if (!signupInputData.getEmail().contains("@")
                || !signupInputData.getEmail().contains(".com")) {
            userPresenter.prepareFailView("Invalid email format");
            return;
        }
        else if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
            return;
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
            return;
        }
        else {
            final User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), signupInputData.getEmail());
            userDataAccessObject.save(user);

            final SignupOutputData signupOutputData = new SignupOutputData(user.getName(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
