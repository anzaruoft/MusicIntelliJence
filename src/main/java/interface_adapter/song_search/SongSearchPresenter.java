package interface_adapter.song_search;

import interface_adapter.ViewManagerModel;
import use_case.song_search.SongSearchOutputBoundary;
import use_case.song_search.SongSearchOutputData;

/**
 * The presenter for the song search use case.
 */
public class SongSearchPresenter implements SongSearchOutputBoundary {

    private final SongSearchViewModel songSearchViewModel;
    private final ViewManagerModel viewManagerModel;

    public SongSearchPresenter(ViewManagerModel viewManagerModel,
                           SongSearchViewModel songSearchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.songSearchViewModel = songSearchViewModel;
    }

    @Override
    public void prepareSuccessView(SongSearchOutputData outputData) {
    }

    @Override
    public void prepareFailView(String error) {
    }

    // TODO: Implement a switch to song rating view.
}
