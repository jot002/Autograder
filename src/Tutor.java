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

    public String fetchMessage(MessageExchange me) {
        if (me == null) {
            throw new IllegalArgumentException();
        }
        if (!this.rooms.contains(me)) {
            throw new IllegalArgumentException();
        }
        String sentence = "";
        for (Message piece :  me.getLog(this)) {
            sentence = sentence + piece.getContents() + "\n";
        }
        return sentence;
    }

    public String displayName() {
        String sentence = String.format("<%s> %s", this.customTitle,
                this.username);
        return sentence;
    }

    public void setCustomTitle(String newTitle) {
        this.customTitle = newTitle;
    }

    public MessageExchange createAutograder(ArrayList<User> users) {
        Autograder autograder = new Autograder(this);
        for (User person : users) {
            try {
                person.joinRoom(autograder);
            }
            catch (OperationDeniedException ODE) {
                ODE.getMessage();
            }
        }
        return autograder;
    }

}
