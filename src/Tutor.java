import java.util.ArrayList;

public class Tutor extends User {

    // instance variable
    private String customTitle;

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
