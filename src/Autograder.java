import java.util.ArrayList;
import java.util.List;

public class Autograder implements MessageExchange {

    // time allowed
    private static final int DEFAULT_ALLOTTED_TIME = 15;

    // max number of messages to fetch
    private static final int MAX_MSG_SIZE = 100;

    // Error message to use in OperationDeniedException
    protected static final String SESSION_ENDED =
            "Session has already ended. Ticket can't be resolved";
    protected static final String NO_ACCESS =
            "Only tutors can actively resolve tickets.";
    protected static final String NO_LOGS =
            "There are no more messages in the log.";



    // instance variables
    private ArrayList<User> users;
    private ArrayList<Message> log;
    private ArrayList<String> results;
    private Tutor tutor;

    public Autograder(Tutor tutor) {
        this.users = new ArrayList<User>();
        this.log = new ArrayList<Message>();
        this.results = new ArrayList<String>();
        this.tutor = tutor;
    }

    public ArrayList<Message> getLog(User requester) {
        if (stopSession()) {
            return null;
        }
        if (requester.getClass() == Tutor) {
            return this.getLog(requester);
        }
        if (requester.getClass() == Student) {
            if (this.getLog(requester).size()<100) {
                return this.getLog(requester);
            }
            else {
                return requester.fetchMessage(this.rooms);
            }
        }
    }
    public ArrayList<String> getResults(){
        /* TODO */
        return null;
    }

    public boolean addUser(User u) {
        /* TODO */
        return false;
    }

    public boolean removeUser(User requester, User u) {
        /* TODO */
        return false;
    }

    public ArrayList<User> getUsers() {
        if ()
        return this.users;
    }

    public boolean recordMessage(Message m) {
        /* TODO */
        return false;
    }

    public String resolveTicket(User requester) throws OperationDeniedException {
        /* TODO */
        return null;
    }
    public boolean stopSession(){
        /*TODO*/
        return false;
    }

}