package interface_adapter.song_search;

import interface_adapter.ViewModel;

public class SongSearchViewModel extends ViewModel<SongSearchState> {
    public SongSearchViewModel() {
        super("song search");
        setState(new SongSearchState());
    }
}
