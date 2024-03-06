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
    public final int EXPECTED_LENGTH = 2;

    /**
     * Constructs a Ui object and initializes the Scanner to read user input.
     */
    public Ui() {
        this.in = new Scanner(System.in);
        this.input = in.nextLine();
    }

    /**
     * Reads the next line of user input and updates the input field.
     */
    public void nextInput() {
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
            if (inputPart.length < EXPECTED_LENGTH || inputPart[1] == null) {
                switch (inputPart[0]) {
                case "todo" :
                case "deadline" :
                case "event" :
                    throw new JaneException("Description for a " + inputPart[0] + " cannot be empty");
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
                parser.markAsDone(Integer.parseInt(inputPart[1]) - 1);
                break;
            case "unmark":
                parser.markAsUndone(Integer.parseInt(inputPart[1]) - 1);
                break;
            case "delete":
                taskList.removeTask(Integer.parseInt(inputPart[1]) - 1);
                break;
            case "find":
                parser.findWord(inputPart[1]);
                break;
            default:
                throw new JaneException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (JaneException e) {
            throw new JaneException(e.getMessage());
        }
    }
}