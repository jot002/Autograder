/*
    Name: Jonathan Tran
    PID:  A15967290
 */
import java.time.LocalDate;

/**
 * Message is created to replicate messages that will be sent into autograder.
 * @author Jonathan Tran
 * @since  10/18/21
 */
public abstract class Message {

    // Error message to use in OperationDeniedException
    protected static final String DENIED_USER_GROUP =
            "This operation is disabled in your user group.";

    // instance variables
    private LocalDate date;
    private User sender;
    protected String contents;

    /**
     * This constructor sets the date to the date today, the sender to sender,
     * and returns an exception if sender is null.
     * @param sender the person who sent the message
     * @exception IllegalArgumentException when sender is null
     */
    public Message(User sender) {
        if (sender == null) {
            throw new IllegalArgumentException();
        }
        this.date = LocalDate.now();
        this.sender = sender;
    }

    /**
     * This method returns the date
     * @return LocalDate that shows the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * This method returns the sender
     * @return User returns the sender
     */
    public User getSender() {
        return this.sender;
    }

    /**
     * This abstract method creates a method that the subclasses
     * have to implement.
     * @return String showing the contents
     */
    public abstract String getContents();

}
