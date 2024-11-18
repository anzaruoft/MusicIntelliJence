package interface_adapter.song_search;

import use_case.song_search.SongSearchInputBoundary;
import use_case.song_search.SongSearchInputData;

/**
 * Controller for the song search use case.
 */
public class SongSearchController {
    private final SongSearchInputBoundary songSearchUseCaseInteractor;

    public SongSearchController(SongSearchInputBoundary songSearchUseCaseInteractor) {
        this.songSearchUseCaseInteractor = songSearchUseCaseInteractor;
    }

    /**
     * Executes the Song Search Use Case.
     * @param songName the name of the song being searched
     */
    public void execute(String songName) {
        final SongSearchInputData songSearchInputData = new SongSearchInputData(songName);
        songSearchUseCaseInteractor.execute(songSearchInputData);
    }
}
