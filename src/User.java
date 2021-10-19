/*
    Name: Jonathan Tran
    PID:  A15967290
 */
import java.util.ArrayList;
/**
 * The User class is the abstract class that defines the functionality
 * of a user in our messaging app.
 * @author Jonathan Tran
 * @since  10/18/21
 */
public abstract class User {

    // Error message to use in OperationDeniedException
    protected static final String JOIN_ROOM_FAILED =
            "Failed to join the chat room.";
    protected static final String INVALID_MSG_TYPE =
            "Cannot send this type of message to the specified room.";

    // instance variables
    protected String username;
    protected String bio;
    protected ArrayList<MessageExchange> rooms;

    /**
     * This constructor sets rooms to a new ArrayList, username to the
     * argument username, and bio to the argument bio.
     * It throws an IllegalArgumentException if bio or username is null.
     * @param username A string that contains the user's username
     * @param bio a String that has the information of the user.
     * @exception IllegalArgumentException when username or bio are null
     */
    public User(String username, String bio) {
        if (bio == null || username == null) {
            throw new IllegalArgumentException();
        }
        this.rooms = new ArrayList<MessageExchange>();
        this.username = username;
        this.bio = bio;
    }

    /**
     * This method sets the original bio to newBio
     * @param newBio a string that will be the new bio
     */
    public void setBio(String newBio) {
        if (newBio == null) {
            throw new IllegalArgumentException();
        }
        this.bio = newBio;
    }

    /**
     * This method displays the bio
     * @return String the bio of the user
     */
    public String displayBio() {
        return this.bio;
    }

    /**
     * This method joins the user into the room.
     * @param me MessageExchange room that the user could join
     * @exception IllegalArgumentException when me is null
     * @exception OperationDeniedException when user is already in the room
     * @exception IllegalArgumentException when the user can not be
     * added into the room
     */
    public void joinRoom(MessageExchange me) throws OperationDeniedException {
        if (me == null) {
            throw new IllegalArgumentException();
        }
        // if the user is already in the room, throw an exception
        if (rooms.contains(this)) {
            throw new OperationDeniedException(JOIN_ROOM_FAILED);
        }
        // if the user can not be added to me
        if (!me.addUser(this)) {
            throw new OperationDeniedException(JOIN_ROOM_FAILED);
        }
    }

    /**
     * This method takes the user out of the room
     * @param me MessageExchange room that the user will quit
     * @exception IllegalArgumentException when me is null
     */
    public void quitRoom(MessageExchange me) {
        if (me == null) {
            throw new IllegalArgumentException();
        }
        // removes the user from me
        me.removeUser(this, this);
    }

    /**
     * This method sends the message to the message exchange room.
     * @param me MessageExchange room that the user will quit
     * @param contents a String containing the contents
     * @param lines the number of lines in the message
     * @exception IllegalArgumentException when me or contents is null
     * @exception IllegalArgumentException when rooms does not contain me
     * @exception OperationDeniedException when inputs are invalid.
     */
    public void sendMessage(MessageExchange me, String contents, int lines) {
        if (me == null) {
            throw new IllegalArgumentException();
        }
        if (contents == null) {
            throw new IllegalArgumentException();
        }
        // if the rooms does not contain me
        if (!this.rooms.contains(me)) {
            throw new IllegalArgumentException();
        }
        try {
            // if lines = -1, that means TextMessage
            if (lines == -1) {
                TextMessage newMessage = new TextMessage(this, contents);
                // send to message exchange room
                me.recordMessage(newMessage);
            }
            // if lines is not -1
            else {
                CodeMessage newMessage = new CodeMessage(this,
                        contents, lines);
                // send to message exchange room
                me.recordMessage(newMessage);
            }
        }
        catch (OperationDeniedException ODE){
            System.out.println(ODE.getMessage());
        }
    }

    /**
     * This abstract method is created so the subclasses have to implement it
     * @return String message from me
     */
    public abstract String fetchMessage(MessageExchange me);

    /**
     * This abstract method is created to display the name
     * @return String name
     */
    public abstract String displayName();
}
