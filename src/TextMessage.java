/*
    Name: Jonathan Tran
    PID:  A15967290
 */
/**
 * TextMessage is created to replicate text messages that will be sent into autograder.
 * @author Jonathan Tran
 * @since  10/18/21
 */
public class TextMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";

    // input validation criteria
    private static final int MAX_TEXT_LENGTH = 500;

    /**
     * This constructor sets the date to the date today, the sender to sender,
     * contents to text and returns an exception if sender or text is null,
     * and throws OperationDeniedException if the text length is greater
     * than the max length.
     * @param sender the person who sent the message
     * @param text the content of the message
     * @exception IllegalArgumentException when sender or text is null
     * @exception OperationDeniedException when the text length is greater
     * than the maximum text length
     */
    public TextMessage(User sender, String text)
            throws OperationDeniedException {
        super(sender);
        if (text.length() > MAX_TEXT_LENGTH) {
            throw new OperationDeniedException(EXCEED_MAX_LENGTH);
        }
        if (sender == null || text == null) {
            throw new IllegalArgumentException();
        }
        this.contents = text;
    }

    /**
     * This method gets the contents of the message and returns it
     * @return String sentence that shows the contents
     */
    // Yuxuan [16:38:36.868882500]: A sample text message.
    public String getContents() {
        String senderName = this.getSender().displayName();;
        String dateTime = this.getDate().toString();
        String sampleText = this.contents;
        String sentence = String.format("%s [%s]: %s", senderName,
                dateTime, sampleText);
        return sentence;
    }

}
