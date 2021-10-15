
public class TextMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";

    // input validation criteria
    private static final int MAX_TEXT_LENGTH = 500;

    public TextMessage(User sender, String text)
            throws OperationDeniedException {
        super(sender);
        this.contents = text;
        if (text.length() > 500) {
            throw new OperationDeniedException(EXCEED_MAX_LENGTH);
        }
        if (sender == null || text == null) {
            throw new IllegalArgumentException();
        }
    }

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
