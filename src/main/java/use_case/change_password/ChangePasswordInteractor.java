package use_case.change_password;

import entity.User;
import entity.UserFactory;

/**
 * The Change Password Interactor.
 */
public class ChangePasswordInteractor implements ChangePasswordInputBoundary {
    private final ChangePasswordUserDataAccessInterface userDataAccessObject;
    private final ChangePasswordOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public ChangePasswordInteractor(ChangePasswordUserDataAccessInterface changePasswordDataAccessInterface,
                                    ChangePasswordOutputBoundary changePasswordOutputBoundary,
                                    UserFactory userFactory) {
        this.userDataAccessObject = changePasswordDataAccessInterface;
        this.userPresenter = changePasswordOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(ChangePasswordInputData changePasswordInputData) {
        if (changePasswordInputData.getPassword() == null || changePasswordInputData.getPassword().trim().isEmpty()) {
            userPresenter.prepareFailView("passwordFailureEmpty");
            return;
        }

        final User user = userFactory.create(changePasswordInputData.getUsername(),
                                             changePasswordInputData.getPassword(),
                changePasswordInputData.getEmail());

        if (user == null) {
            userPresenter.prepareFailView("userCreationFailure");
            return;
        }

        if (!changePasswordInputData.getPassword().isEmpty()) {
            userDataAccessObject.changePassword(user);

            final ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(user.getName(),
                    false);
            userPresenter.prepareSuccessView(changePasswordOutputData);
        }
    }

    @Override
    public void switchToChangePasswordView() {
        userPresenter.switchToChangePasswordView();
    }
}
