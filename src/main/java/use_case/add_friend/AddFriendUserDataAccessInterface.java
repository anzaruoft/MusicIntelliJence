package use_case.add_friend;

import entity.User;

/**
 * DAO for the Add Friend Use Case.
 */

public interface AddFriendUserDataAccessInterface {

    /**
     * Checks if the given username is already a friend exists.
     * @param friendUserName the username to look for
     * @param user the user
     * @return true if a user with the given username friends with the user; false otherwise.
     */
    boolean isFriend(String friendUserName, String user);
    /**
     * Checks if username has sent a friend request.
     * @param friendUserName the username to look for
     * @param user the user
     * @return true if the username has sent a friend request, false otherwise.
     */

    boolean sentFriendRequest(String friendUserName, String user);

    /**
     * Checks if username has recieved a friend request.
     * @param friendUserName the username to look for
     * @param user the user
     * @return true if the username has sent a friend request, false otherwise.
     */
    boolean recievedFriendRequest(String friendUserName, String user);

    /**
     * Adds username as friend if the given username is not a friend and hasn't recieved a friend request from user.
     * @param friendUserName the username to look for
     * @param user the user
     */

    void addFriend(String friendUserName, String user);

}
