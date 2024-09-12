package dukeRobot.Tools;


import java.util.InputMismatchException;

/**
 * represents the parser to parse the commands into dukeRobot understandable contents
 * data members are the necessary inputs into every commands objects
 * A <code>Parser</code> corresponds to the parser object that contains parsed commands information.
 */
public class Parser {
    private String keyWord = null;
    private String description = null;
    private String by = null;
    private String taskIndex = null;
    private String from = null;
    private String to = null;
    private Ui ui;
    public Parser(String userInput, Ui ui) throws IndexOutOfBoundsException {
        this.ui = ui;
        if (userInput.equals("list")) {
            this.keyWord = "list";
        } else if (userInput.equals("help")){
            this.keyWord = "help";
        } else {
            String[] commandSplit = userInput.split(" ", 2);

            this.keyWord = commandSplit[0];
            if (commandSplit.length > 1) {
                this.taskIndex = commandSplit[1];
            } else {
                this.taskIndex = "0";
            }
        }

        switch(keyWord) {
            case "deadline":
                try {
                    String descriptionAndBy = userInput.split(" ", 2)[1];
                    try {
                        this.description = descriptionAndBy.split("/")[0];
                        this.by = descriptionAndBy.split("/")[1];
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.emptyByDeadlineError();
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.emptyDescriptionByDeadlineError();
                    break;
                }
            case "event":
                String descriptionAndRange = userInput.split(" ", 2)[1];
                this.description = descriptionAndRange.split("/",3)[0];
                this.from = descriptionAndRange.split("/",3)[1];
                this.to = descriptionAndRange.split("/", 3)[2];
                break;

            case "todo":
                try {
                    this.description = userInput.split(" ", 2)[1];
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.emptyDescriptionError();
                    break;
                }
            // BYE, MARK, UNMARK, LIST,DELETE, HELP
            default:
                this.from = null;
                this.to = null;
                this.description = null;
                this.by = null;

        }

    }

    public int getTaskIndex() {
        try{
            return Integer.parseInt(this.taskIndex);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getWord() {
        return this.taskIndex;
    }

    public String getDescription() {
        return this.description;
    }
    public String getBy() {
        return this.by;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

}
