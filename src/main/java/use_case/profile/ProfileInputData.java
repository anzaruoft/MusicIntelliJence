package use_case.profile;

/**
 * The Input Data for the Profile Use Case.
 */
public class ProfileInputData {

    private final String username;

    public ProfileInputData(String username) {
        this.username = username;
    }

    public String getName() {
        return username;
    }
}
