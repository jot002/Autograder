/*
    Name: Jonathan Tran
    PID:  A15967290
 */
import java.util.ArrayList;
/**
 * The Student class extends the User class. Users belonging to this
 * class will have a limitation when fetching a message.
 * @author Jonathan Tran
 * @since  10/18/21
 */
public class Student extends User {

    // Message to append when fetching non-text message
    private static final String FETCH_DENIED_MSG =
            "This message cannot be fetched because you are not a premium user.";

    // max number of messages to fetch
    private static final int MAX_MSG_SIZE = 100;

    /**
     * This constructor uses super to get set the same constructor
     * from the User class.
     * It throws an IllegalArgumentException if bio or username is null.
     * @param username A string that contains the user's username
     * @param bio a String that has the information of the user.
     * @exception IllegalArgumentException when username or bio are null
     */
    public Student(String username, String bio) {
        super(username, bio);
    }

    /**
     * This method fetches a message from the MessageExchange me
     * @param me MessageExchange where the message will be taken from
     * @return String message from me
     * @exception IllegalArgumentException when me is null
     * @exception IllegalArgumentException when rooms does not contain me
     */
    public String fetchMessage(MessageExchange me) {
        if (me == null) {
            throw new IllegalArgumentException();
        }
        // if the rooms does not contain me
        if (!this.rooms.contains(me)) {
            throw new IllegalArgumentException();
        }
        if (me.getLog(this).size() < MAX_MSG_SIZE) {
            String sentence = "";
            // goes through all the messages
            for (Message piece :  me.getLog(this)) {
                // checks if the message is a TextMessage
                if (piece instanceof TextMessage) {
                    sentence = sentence + piece.getContents() + "\n";
                }
                // when the message is not a TextMessage
                else {
                    sentence = sentence + FETCH_DENIED_MSG + "\n";
                }
            }
            return sentence;
        }
        // fetch the last 100 messages
        else {
            String sentence = "";
            // getting the last 100 messages only
            for (int i = me.getLog(this).size() - MAX_MSG_SIZE;
                 i < me.getLog(this).size(); i++) {
                // checks if the message is a TextMessage
                if (me.getLog(this).get(i) instanceof TextMessage) {
                    sentence = sentence + me.getLog(this).get(i) + "\n";
                }
                else {
                    sentence = sentence + FETCH_DENIED_MSG + "\n";
                }
            }
            return sentence;
        }
    }

    /**
     * This method returns the username of the student
     * @return String username of the student
     */
    public String displayName() {
        return this.username;
    }
}
