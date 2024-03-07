import daisy.error.*;
import daisy.parser.Parser;
import daisy.storage.Storage;
import daisy.tasklist.TaskList;
import daisy.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The Daisy program implements check list application for the user to keep track of a list of tasks. The program works with
 * 3 kind of tasks in total: todo, deadline and event. For the list of commands and input format, please check the user guide.
 */

public class Daisy {

    protected static String defaultFileLocation = System.getProperty("user.dir") + "\\src\\main\\java\\daisy\\data\\Daisy.txt";

    /**
     * Main running process for the Daisy application
     */
    public static void main(String[] args) {

        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Storage storage = new Storage(defaultFileLocation);

        storage.loadData(tasks);

        ui.sendIntro();

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while (!command.equals("bye")) {
            ui.setLineBreak();
            Parser userInput = new Parser(command);
            switch (userInput.getAction()) {
                case "list":
                    tasks.listTasks();
                    break;
                case "find":
                    try {
                        String keyWord = userInput.getFindInfo();
                        tasks.findTasks(keyWord);
                    } catch (IllegalFindFormatException e) {
                        ui.printTodoMissingError();
                    }
                    break;
                case "mark":
                    try {
                        int taskNo = userInput.getIndexFromCommand();
                        tasks.markDone(taskNo, ui);
                    } catch (MissingIndexException | NumberFormatException e) {
                        ui.printIndexMissingError();
                    }
                    break;
                case "unmark":
                    try {
                        int taskNo = userInput.getIndexFromCommand();
                        tasks.markUndone(userInput.getIndexFromCommand(), ui);
                    } catch (MissingIndexException | NumberFormatException e) {
                        ui.printIndexMissingError();
                    }
                    break;
                case "delete":
                    try {
                        int taskNo = userInput.getIndexFromCommand();
                    tasks.deleteTask(userInput.getIndexFromCommand());
                    } catch (MissingIndexException | NumberFormatException e) {
                        ui.printIndexMissingError();
                    }
                    break;
                case "todo":
                    try {
                        tasks.createTodo(userInput.getTodoInfo(),true, false);
                    } catch (IllegalTodoFormatException e) {
                        ui.printTodoMissingError();
                    }
                    break;
                case "deadline":
                    try {
                        String[] separate_deadlines = userInput.getDeadlineInfo();
                        tasks.createDeadline(separate_deadlines[0],separate_deadlines[1], true, false);
                    } catch (IllegalDeadlineFormatException | DateTimeParseException e) {
                        ui.printDeadlineInputError();
                    }
                    break;
                case "event":
                    try {
                        String[] separate_events = userInput.getEventInfo();
                        tasks.createEvent(separate_events[0],separate_events[1], separate_events[2], true, false);
                    } catch (IllegalEventFormatException | DateTimeParseException e){
                        ui.printEventInputError();
                    }
                    break;
                default:
                    try {
                        throw new IllegalEntryException();
                    } catch (IllegalEntryException e){
                        ui.printInvalidCommandError();
                    }
                    break;
            }
            ui.setLineBreak();
            command = in.nextLine();
        }

        ui.sendExit();

        storage.saveData(tasks);

    }
}
