import java.time.LocalDate;

public abstract class Message {

    // Error message to use in OperationDeniedException
    protected static final String DENIED_USER_GROUP =
            "This operation is disabled in your user group.";

    // instance variables
    private LocalDate date;
    private User sender;
    protected String contents;

    public Message(User sender) {
        if (sender == null) {
            throw new IllegalArgumentException();
        }
        this.date = LocalDate.now();
        this.sender = sender;
    }

    public LocalDate getDate() {
        return date;
    }

    public User getSender() {
        return sender;
    }

    public abstract String getContents();

}