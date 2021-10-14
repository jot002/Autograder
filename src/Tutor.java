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
        String sentence = me.getLog(this).getContents() + "\n";
        return sentence;
    }

    public String displayName() {
        String sentence = String.format("<%> %", this.customTitle,
                this.username);
        return sentence;
    }

    public void setCustomTitle(String newTitle) {
        this.customTitle = newTitle;
    }

    public MessageExchange createAutograder(ArrayList<User> users) {
        /* TODO */
        return null;
    }

}
