/*
    Name: Jonathan Tran
    PID:  A15967290
 */
import java.util.ArrayList;
/**
 * The Tutor class extends the User class. Users belonging to this class
 * will have no limitation of fetching messages. Also, they will have a
 * custom title that will always display before the name.
 * @author Jonathan Tran
 * @since  10/18/21
 */
public class Tutor extends User {

    // instance variable
    private String customTitle;
    /**
     * This constructor uses super to get set the same constructor
     * from the User class. It adds the customTitle of "Tutor".
     * It throws an IllegalArgumentException if bio or username is null.
     * @param username A string that contains the user's username
     * @param bio a String that has the information of the user.
     * @exception IllegalArgumentException when username or bio are null
     */
    public Tutor(String username, String bio) {
        super(username, bio);
        this.customTitle = "Tutor";
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
        if (!this.rooms.contains(me)) {
            throw new IllegalArgumentException();
        }
        String sentence = "";
        // goes through the messages of me
        for (Message piece :  me.getLog(this)) {
            sentence = sentence + piece.getContents() + "\n";
        }
        return sentence;
    }

    /**
     * This method displays the name of the tutor
     * @return String name of the tutor
     */
    public String displayName() {
        String sentence = String.format("<%s> %s", this.customTitle,
                this.username);
        return sentence;
    }

    /**
     * This method sets the custom title to a new title
     * @param newTitle the new title that will the custom title.
     */
    public void setCustomTitle(String newTitle) {
        this.customTitle = newTitle;
    }

    /**
     * This method creates the autograder that will be used by the other classes.
     * @param users an ArrayList<User> that contains all the users
     * @return MessageExchange a platform where the autograder is created.
     * @exception OperationDeniedException
     */
    public MessageExchange createAutograder(ArrayList<User> users) {
        // creates a new autograder
        Autograder autograder = new Autograder(this);
        // iterates through the people in users
        for (User person : users) {
            // tries to have them join the autograder
            try {
                person.joinRoom(autograder);
            }
            // sends a message if they can't join
            catch (OperationDeniedException ODE) {
                ODE.getMessage();
            }
        }
        return autograder;
    }

}
