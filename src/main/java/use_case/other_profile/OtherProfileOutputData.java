package use_case.other_profile;

/**
 * Output Data for the Other Profile Use Case.
 */
public class OtherProfileOutputData {
    private final String thisUsername;
    private String response;

    public OtherProfileOutputData(String thisUsername,
                                  String response) {
        this.thisUsername = thisUsername;
        this.response = response;
    }

    public String getThisUsername() {
        return thisUsername;
    }

    public String getResponse() {
        return response;
    }
}
