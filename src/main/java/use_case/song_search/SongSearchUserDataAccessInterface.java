package use_case.song_search;

/**
 * DAO for the Song Search Use Case.
 */
public interface SongSearchUserDataAccessInterface {

    /**
     * Checks if the given song exists in the data source.
     *
     * @param songName the name of the song to search for
     * @return true if a song with the given name exists, false otherwise
     */
    boolean existsByName(String songName);

    /**
     * Retrieves the song with the given name from the data source.
     *
     * @param songName the name of the song to retrieve
     * @return the song name if found, or null if not found
     */
    String get(String songName);
}
