package interface_adapter.song_search;

import interface_adapter.ViewModel;
import interface_adapter.change_password.LoggedInState;

/**
 * The view model for the Song Search view.
 */
public class SongSearchViewModel extends ViewModel<SongSearchState> {

    public SongSearchViewModel() {
        super("song search");
        setState(new SongSearchState());
    }
}