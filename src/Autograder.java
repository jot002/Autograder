/*
    Name: Jonathan Tran
    PID:  A15967290
 */
import java.util.ArrayList;
import java.util.List;
/**
 * the Autograder class is created to implement MessageExchange.
 * It moderates communication by keeping logs of messages of users.
 * @author Jonathan Tran
 * @since  10/18/21
 */
public class Autograder implements MessageExchange {

    // time allowed
    private static final int DEFAULT_ALLOTTED_TIME = 15;

    // max number of messages to fetch
    private static final int MAX_MSG_SIZE = 100;

    private static final double TENTH_CONVERSION = 10.0;

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

    /**
     * This constructor sets the users, log, and results to a new ArrayList.
     * It sets tutor to the argument tutor and adds the tutor to the
     * autograder.
     * @param tutor the tutor that is joining the autograder.
     */
    public Autograder(Tutor tutor) {
        this.users = new ArrayList<User>();
        this.log = new ArrayList<Message>();
        this.results = new ArrayList<String>();
        this.tutor = tutor;
        this.addUser(tutor);
    }

    /**
     * This method gets the message log of the autograder.
     * @param requester the requester is a User that wants the logs
     * @return ArrayList of messages that shows the messages
     */
    public ArrayList<Message> getLog(User requester) {
        if (this.tutor == null) {
            return null;
        }
        // checks if requester is an instance of Tutor
        if (requester instanceof Tutor) {
            return this.log;
        }
        // checks if requester is an instance of Student
        if (requester instanceof Student) {
            if (this.log.size() < MAX_MSG_SIZE) {
                return this.log;
            }
            else {
                ArrayList<Message> newLog = new ArrayList<Message>();
                // goes through the index of the log from the back
                for (int i = this.log.size() - MAX_MSG_SIZE;
                       i < this.log.size(); i++) {
                newLog.add(this.log.get(i));
                }
                return newLog;
            }
        }
        return null;
    }

    /**
     * This method gets the results of the autograder room
     * @return ArrayList of strings that shows the results.
     */
    public ArrayList<String> getResults(){
        if (this.tutor == null) {
            return null;
        }
        return this.results;
    }

    /**
     * This method adds the user u to the autograder room
     * @param u A user that will be added
     * @return boolean stating true if the user got added, false if not
     */
    public boolean addUser(User u) {
        if (this.tutor == null) {
            return false;
        }
        if (!this.users.contains(u)) {
            // adds u if u is not in users
            this.users.add(u);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method removes the user from the autograder.
     * @return boolean stating true if the person got removed, false if not
     */
    public boolean removeUser(User requester, User u) {
        if (this.tutor == null) {
            return false;
        }
        if (!this.users.contains(u)) {
            return false;
        }
        // checks the type of requester and u
        if (requester instanceof Tutor && u instanceof Student) {
            this.users.remove(u);
            u.rooms.remove(u);
            return true;
        }
        // checks the type of requester and u
        if (requester instanceof Student && u instanceof Student) {
            this.users.remove(u);
            u.rooms.remove(u);
            return true;
        }
        return false;
    }

    /**
     * This method gets all the users in the autograder room
     * @return ArrayList containing the users who are in the room
     */
    public ArrayList<User> getUsers() {
        if (this.tutor == null) {
            return null;
        }
        return this.users;
    }

    /**
     * This method records a message and adds it the log.
     * @param m a Message
     * @return boolean stating true if it it got added, false if not
     */
    public boolean recordMessage(Message m) {
        if (this.tutor == null) {
            return false;
        }
        log.add(m);
        return true;
    }

    /**
     * This method resolves a ticket and checks the type of messages that are
     * in the log. if the session has ended, it will throw exceptions.
     * @param requester a User that is asking to resolve the ticket
     * @return String setnence that tells how many resolved and unresolved
     * tickets there are.
     * @exception OperationDeniedException when session has ended
     * @exception OperationDeniedException if the requester is a student
     * @exception OperationDeniedException the size of the log is 0
     */
    public String resolveTicket(User requester) throws OperationDeniedException {
        if (this.tutor == null) {
            throw new OperationDeniedException(SESSION_ENDED);
        }
        if (requester instanceof Student) {
            throw new OperationDeniedException(NO_ACCESS);
        }
        if (this.log.size() == 0) {
            throw new OperationDeniedException(NO_LOGS);
        }
        // goes through all the messages of this.log
        for (Message logX : this.log) {
            // checks if logX is an instance of CodeMessage
            if (logX instanceof CodeMessage) {
                int numLines = ((CodeMessage) logX).getLines();
                // gets how much the time will be
                int answer = (int) Math.ceil(numLines / TENTH_CONVERSION);
                if (answer > DEFAULT_ALLOTTED_TIME) {
                    int resolved = DEFAULT_ALLOTTED_TIME;
                    int unresolved = answer - DEFAULT_ALLOTTED_TIME;
                    String sentence = String.format("This ticket resolves %d " +
                            "lines, %d lines unresolved", resolved, unresolved);
                    this.results.add(sentence);
                    return sentence;
                }
                else {
                    int resolved= answer;
                    int unresolved = 0;
                    // creates a sentence describing the lines
                    String sentence = String.format("This ticket resolves %d " +
                            "lines, %d lines unresolved", resolved, unresolved);
                    this.results.add(sentence);
                    return sentence;
                }
            }
            // checks if logX is not an instance of CodeMessage
            else {
                String sentence = "This ticket doesn’t resolve a codeMessage";
                this.results.add(sentence);
                return sentence;
            }
        }
        return null;
    }

    /**
     * This method ends the autograder session.
     * @return boolean stating true if the session ended, false if it was
     * already ended
     */
    public boolean stopSession(){
        // checks if the session has ended already
        if (this.tutor == null) {
            return false;
        }
        this.users = new ArrayList<User>();
        // goes through all the users
//        for (User person : this.users) {
//            // removes everyone from users
//            this.users.remove(person);
//        }
        // removes all messages from the log
        this.log = new ArrayList<Message>();
//        for (Message logX : this.log) {
//            this.log.remove(logX);
//        }
        this.tutor = null;
        return true;
    }

}
