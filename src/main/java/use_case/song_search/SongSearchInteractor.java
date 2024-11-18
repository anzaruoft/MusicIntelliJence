package use_case.song_search;

/**
 * The Song Search Interactor.
 */
public class SongSearchInteractor implements SongSearchInputBoundary {

    private final SongSearchUserDataAccessInterface songSearchUserDataAccessInterface;
    private final SongSearchOutputBoundary songSearchOutputBoundary;

    public SongSearchInteractor(SongSearchUserDataAccessInterface songSearchUserDataAccessInterface,
                                SongSearchOutputBoundary songSearchOutputBoundary) {
        this.songSearchUserDataAccessInterface = songSearchUserDataAccessInterface;
        this.songSearchOutputBoundary = songSearchOutputBoundary;
    }

    @Override
    public void execute(SongSearchInputData songSearchInputData) {
    }
}
