import java.util.Arrays;
public class CodeMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String INVALID_INPUT =
            "The source path is not valid.";

    // Error message to use in OperationDeniedException for the invalid line number
    private static final String INVALID_CODE =
            "The files are not long enough.";

    // input validation criteria
    private static final String[] ACCEPTABLE_EXTENSIONS =
            new String[]{"html", "java", "py", "mjs", "ipynb", "md", "yml"};

    // instance variable
    private String extension;
    private int lines;

    public CodeMessage(User sender, String codeSource, int lines)
            throws OperationDeniedException {
        super(sender);
        this.contents = codeSource;
        String[] subparts = codeSource.split("\\.");
        String lastPart = subparts[subparts.length - 1];
        this.extension = lastPart.toLowerCase();
        this.lines = lines;
        if (!Arrays.asList(ACCEPTABLE_EXTENSIONS).contains(extension)) {
            throw new OperationDeniedException(INVALID_INPUT);
        }
        if (lines < 10) {
            throw new OperationDeniedException(INVALID_CODE);
        }
        if (sender == null || codeSource == null) {
            throw new IllegalArgumentException();
        }
    }

    public String getContents() {
        String senderName = this.getSender().displayName();;
        String dateTime = this.getDate().toString();
        String sampleText = this.contents;
        String sentence = String.format("% [%]: Code at %", senderName,
                dateTime, sampleText);
        return sentence;
    }

    public String getExtension() {
        return this.extension;
    }

    public int getLines() {
        return this.lines;
    }

}
