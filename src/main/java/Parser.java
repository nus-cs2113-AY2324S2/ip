/**
 * The Parser class is responsible for parsing the input from the user and calling the appropriate methods in TaskList.
 * A <code>Parser</code> object represents the parser.
 */
public class Parser {

    private Storage storageParser;

    /**
     * Parses the input and calls the appropriate method in TaskList
     *
     * @param input The input from the user
     * @param taskList The task list
     * @throws ChandlerException If the input is invalid
     */
    public void parseInput(String input, TaskList taskList) throws ChandlerException {
        String[] words = input.split("\\s+");
        String inputCommand = words[0].toUpperCase();

        if (words.length <= 1 && !inputCommand.equals("LIST") && !inputCommand.equals("BYE") && !inputCommand.equals("HELP")) {
            throw new ChandlerException("You need to specify a task.");
        }

        switch (inputCommand) {
            case "LIST":
                taskList.listTasks();
                break;
            case "MARK":
                taskList.markTaskAsDone(Integer.parseInt(input.replace("mark ", "")) - 1);
                break;
            case "UNMARK":
                taskList.markTaskAsUndone(Integer.parseInt(input.replace("unmark ", "")) - 1);
                break;
            case "DELETE":
                taskList.deleteTask(Integer.parseInt(input.replace("delete ", "")) - 1);
                break;
            case "TODO":
                taskList.addTodo(input.replace("todo ", ""));
                break;
            case "DEADLINE":
                taskList.addDeadline(input.replace("deadline ", ""));
                break;
            case "EVENT":
                taskList.addEvent(input.replace("event ", ""));
                break;
            case "FIND":
                taskList.findMatchingTasks(input.replace("find ", ""));
                break;
            case "HELP":
                taskList.printHelpMessage();
                break;
            case "BYE":
                break;
            default:
                throw new ChandlerException("You need to specify if it's a todo, deadline, or event.");
        }
    }

    /**
     * Parses the words in the input line from the file and creates a task from it
     *
     * @param line The line from the file
     * @param taskList The task list
     * @throws ChandlerException If the line is corrupted
     */
    public void parseLineFromFile(String line, TaskList taskList) throws ChandlerException {
        String[] parts = line.split(" \\| "); // Assuming the format is: Type | Status | Description | Additional Info (optional)
        String taskType = parts[0];
        storageParser = new Storage();
        if (parts.length >= 3) {
            int doneStatus = Integer.parseInt(parts[1]);
            String description = parts[2];
            String additionalInfo = parts.length > 3 ? parts[3] : "";
            storageParser.createTaskFromParsedLine(taskType, doneStatus, description, additionalInfo, taskList);
        } else {
            throw new ChandlerException("Corrupted data: Unexpected format in the file.");
        }
    }

}
