/*
    Name: Jonathan Tran
    PID:  A15967290
 */
/**
 * CodeMessage is created to replicate code messages that will be sent into autograder.
 * @author Jonathan Tran
 * @since  10/18/21
 */
import java.util.Arrays;
public class CodeMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String INVALID_INPUT =
            "The source path is not valid.";

    // Error message to use in OperationDeniedException for the invalid line number
    private static final String INVALID_CODE =
            "The files are not long enough.";

    private static final int CHECKING_LENGTH = 10;

    // input validation criteria
    private static final String[] ACCEPTABLE_EXTENSIONS =
            new String[]{"html", "java", "py", "mjs", "ipynb", "md", "yml"};

    // instance variable
    private String extension;
    private int lines;

    /**
     * This constructor sets the date to the date today, the sender to sender,
     * contents to codeSource, lines to lines, and returns an exception if
     * sender or text is null, and throws OperationDeniedException if lines
     * is less than 10 or if the extension is not acceptable. It also
     * sets extension to the extension of the codeSource.
     * @param sender the person who sent the message
     * @param codeSource the source of the code
     * @param lines the amount of lines of messages
     * @exception IllegalArgumentException when sender or text is null
     * @exception OperationDeniedException when lines is less than 10
     * @exception OperationDeniedException when extension is not acceptable
     */
    public CodeMessage(User sender, String codeSource, int lines)
            throws OperationDeniedException {
        super(sender);
        // splits the codeSource to get the extension
        String[] subparts = codeSource.split("\\.");
        String lastPart = subparts[subparts.length - 1];
        this.extension = lastPart.toLowerCase();
        if (!Arrays.asList(ACCEPTABLE_EXTENSIONS).contains(extension)) {
            throw new OperationDeniedException(INVALID_INPUT);
        }
        if (lines < CHECKING_LENGTH) {
            throw new OperationDeniedException(INVALID_CODE);
        }
        if (sender == null) {
            throw new IllegalArgumentException();
        }
        if (codeSource == null) {
            throw new IllegalArgumentException();
        }
        this.lines = lines;
        this.contents = codeSource;
    }

    /**
     * This method gets the contents of the message
     * @return String sentence that displays the contents.
     */
    public String getContents() {
        String senderName = this.getSender().displayName();;
        String dateTime = this.getDate().toString();
        String sampleText = this.contents;
        // creates a sentence with all the details
        String sentence = String.format("% [%]: Code at %", senderName,
                dateTime, sampleText);
        return sentence;
    }

    /**
     * This method gets the extension
     * @return String extension of the codeSource
     */
    public String getExtension() {
        return this.extension;
    }

    /**
     * This method gets the number of lines of the message
     * @return int number of lines
     */
    public int getLines() {
        return this.lines;
    }

}
