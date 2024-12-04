package use_case.other_profile;

/**
 * The Input Data for the Other Profile Use Case.
 */
public class OtherProfileInputData {

    private final String otherUsername;
    private final String thisUsername;

    public OtherProfileInputData(String otherUsername, String thisUsername) {
        this.otherUsername = otherUsername;
        this.thisUsername = thisUsername;
    }

    public String getOtherName() {
        return otherUsername;
    }

    public String getThisName() {
        return thisUsername;
    }
}
