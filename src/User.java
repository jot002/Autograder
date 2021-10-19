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

    public void setBio(String newBio) {
        if (newBio == null) {
            throw new IllegalArgumentException();
        }
        this.bio = newBio;
    }

    public String displayBio() {
        return this.bio;
    }

    public void joinRoom(MessageExchange me) throws OperationDeniedException {
        if (me == null) {
            throw new IllegalArgumentException();
        }
        if (rooms.contains(this)) {
            throw new OperationDeniedException(JOIN_ROOM_FAILED);
        }
        if (me.addUser(this) == false) {
            throw new OperationDeniedException(JOIN_ROOM_FAILED);
        }
    }

    public void quitRoom(MessageExchange me) {
        if (me == null) {
            throw new IllegalArgumentException();
        }
        me.removeUser(this, this);

    }

    public void sendMessage(MessageExchange me, String contents, int lines) {
        if (me == null || contents == null) {
            throw new IllegalArgumentException();

        }
        if (!this.rooms.contains(me)) {
            throw new IllegalArgumentException();
        }
        try {
            if (lines == -1) {
                TextMessage newMessage = new TextMessage(this, contents);
                // send to message exchange room
                me.recordMessage(newMessage);
            }
            else {
                CodeMessage newMessage = new CodeMessage(this, contents, lines);
                // send to message exchange room
                me.recordMessage(newMessage);
            }
        }
        catch (OperationDeniedException ODE){
            System.out.println(ODE.getMessage());
        }

    }

    public abstract String fetchMessage(MessageExchange me);

    public abstract String displayName();
}
