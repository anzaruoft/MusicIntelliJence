package use_case.song_search;

/**
 * The Output Boundary for the Song Search Use Case.
 */
public interface SongSearchOutputBoundary {
    /**
     * Prepares the success view for the Song Search Use Case.
     *
     * @param outputData the output data
     */
    void prepareSuccessView(SongSearchOutputData outputData);

    /**
     * Prepares the failure view for the Login Use Case.
     *
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
