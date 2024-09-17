import java.util.Scanner;

/**
 * Represents user input processing.
 */
public class Ui {
    /** User input string. */
    public String input;

    /** Scanner object for reading user input. */
    public Scanner in;

    /** Expected length of user input. One keyword and subsequent description. */
    public static final int EXPECTED_INPUT_LENGTH = 2;

    /**
     * Constructs a Ui object and initializes the Scanner to read user input.
     */
    public Ui() {
        this.in = new Scanner(System.in);
        this.input = in.nextLine();
    }

    /**
     * Processes the user input, delegating the command to the appropriate parser.
     *
     * @param input The user's input.
     * @param taskList The TaskList object to be manipulated.
     * @throws JaneException If an error occurs during command processing.
     */
    public void processInput(String input, TaskList taskList) throws JaneException {
        try {
            // Splitting the input into two parts for command processing
            String[] inputPart = input.split(" ", 2);

            // Checking if the input has the expected length and is not null for certain commands
            if (inputPart.length < EXPECTED_INPUT_LENGTH || inputPart[1] == null) {
                switch (inputPart[0]) {
                case "todo" :
                case "deadline" :
                case "event" :
                    throw new JaneException(Message.emptyDescriptionError(inputPart[0]));
                case "mark" :
                case "unmark" :
                case "delete" :
                    throw new JaneException(Message.missingSequenceNumberError(inputPart[0]));
                }
            }

            // Creating a parser and executing the appropriate command based on user input
            Parser parser = new Parser(taskList);
            switch (inputPart[0]) {
            case "todo":
                parser.processTodo(inputPart[1]);
                break;
            case "deadline":
                parser.processDeadline(inputPart[1]);
                break;
            case "event":
                parser.processEvent(inputPart[1]);
                break;
            case "list":
                taskList.printList();
                break;
            case "mark":
                parser.markAsDone(inputPart[1]);
                break;
            case "unmark":
                parser.markAsUndone(inputPart[1]);
                break;
            case "delete":
                taskList.removeTask(inputPart[1]);
                break;
            case "find":
                parser.findWord(inputPart[1]);
                break;
            default:
                throw new JaneException(Message.INPUT_NOT_UNDERSTOOD_ERROR);
            }
        } catch (JaneException e) {
            throw new JaneException(e.getMessage());
        }
    }

    /**
     * Reads the next line of user input and updates the input field.
     */
    public void nextInput() {
        this.input = in.nextLine();
    }
}