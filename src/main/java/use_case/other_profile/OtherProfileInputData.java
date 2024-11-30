package use_case.other_profile;

/**
 * The Input Data for the Other Profile Use Case.
 */
public class OtherProfileInputData {

    private final String otherUsername;

    public OtherProfileInputData(String otherUsername) {
        this.otherUsername = otherUsername;
    }

    public String getOtherName() {
        return otherUsername;
    }
}
