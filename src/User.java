import java.util.ArrayList;

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

    public User(String username, String bio) {
        this.rooms = new ArrayList<MessageExchange>();
        this.username = username;
        this.bio = bio;
        if (bio == null || username == null) {
            throw new IllegalArgumentException();
        }
    }

    public void setBio(String newBio) {
        this.bio = newBio;
        if (newBio == null) {
            throw new IllegalArgumentException();
        }
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
        if (!rooms.contains(this)) {
            throw new IllegalArgumentException();
        }
        try {
            if (lines == -1) {
                TextMessage newMessage = new TextMessage(this, contents);
            }
        }
        catch (OperationDeniedException ODE){
            System.out.println(ODE.getMessage());
        }
        // send to message exchange room
    }

    public abstract String fetchMessage(MessageExchange me);

    public abstract String displayName();
}
