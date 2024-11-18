package use_case.song_search;

/**
 * Input boundary for actions which are related to song search.
 */
public interface SongSearchInputBoundary {

    /**
     * Executes the login use case.
     * @param songSearchInputData the input data
     */
    void execute(SongSearchInputData songSearchInputData);
}
