package use_case.song_search;

import entity.User;

/**
 * This is the SongSearchUserDataAccessInterface interface.
 */
public interface SongSearchUserDataAccessInterface {

    /**
     * Checks if the given song exists.
     * @param songTitle the songTitle.
     * @return true if a song exists; false otherwise.
     */
    boolean existsByName(String songTitle);

    /**
     * Returns the song title with the given songTitle.
     * @param songTitle the username to look up.
     * @return the song title with the given songTitle.
     */
    User get(String songTitle);
}
