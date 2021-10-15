import java.util.ArrayList;

public class Student extends User {

    // Message to append when fetching non-text message
    private static final String FETCH_DENIED_MSG =
            "This message cannot be fetched because you are not a premium user.";

    // max number of messages to fetch
    private static final int MAX_MSG_SIZE = 100;

    public Student(String username, String bio) {
        super(username, bio);
    }

    public String fetchMessage(MessageExchange me) {
        if (me == null) {
            throw new IllegalArgumentException();
        }
        if (!this.rooms.contains(me)) {
            throw new IllegalArgumentException();
        }
        if (me.getLog(this).size() < MAX_MSG_SIZE) {
            String sentence = "";
            for (Message piece :  me.getLog(this)) {
                if (piece instanceof TextMessage) {
                    sentence = sentence + piece.getContents() + "\n";
                }
                else {
                    sentence = sentence + FETCH_DENIED_MSG + "\n";
                }
            }
            return sentence;
        }
        // fetch the last 100 messages
        else {
            String sentence = "";
            for (int i = me.getLog(this).size() - MAX_MSG_SIZE;
                 i < me.getLog(this).size(); i++) {
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

    public String displayName() {
        return this.username;
    }
}
