package use_case.song_search;

/**
 * Input boundary for actions related to song search.
 */
public interface SongSearchInputBoundary {

    /**
     * Executes the song search use case.
     * @param songSearchInputData the input data for the song search
     */
    void execute(SongSearchInputData songSearchInputData);

    /**
     * Updates the view model with the result of the song search.
     * @param result the result to be displayed in the view
     */
    void updateViewModel(String result);
}
