import daisy.error.IllegalEntryException;
import daisy.error.IllegalEventFormatException;
import daisy.error.IllegalDeadlineFormatException;
import daisy.error.MissingInformationException;
import daisy.parser.Parser;
import daisy.storage.Storage;
import daisy.tasklist.TaskList;
import daisy.ui.Ui;

import java.util.Scanner;

public class Daisy {

    protected static String defaultFileLocation = System.getProperty("user.dir") + "\\src\\main\\java\\daisy\\data\\Daisy.txt";

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
                        if (keyWord == null) {
                            throw new MissingInformationException();
                        }
                        tasks.findTasks(keyWord);
                    } catch (MissingInformationException e) {
                        ui.printTodoMissingError();
                    }
                    break;
                case "mark":
                    try {
                        int taskNo = userInput.getIndexFromCommand();
                        if (taskNo == -1) {
                            throw new MissingInformationException();
                        }
                        tasks.markDone(taskNo, ui);
                    } catch (MissingInformationException e) {
                        ui.printIndexMissingError();
                    }
                    break;
                case "unmark":
                    try {
                        int taskNo = userInput.getIndexFromCommand();
                        if (taskNo == -1) {
                            throw new MissingInformationException();
                        }
                        tasks.markUndone(userInput.getIndexFromCommand(), ui);
                    } catch (MissingInformationException e) {
                        ui.printIndexMissingError();
                    }
                    break;
                case "delete":
                    try {
                        int taskNo = userInput.getIndexFromCommand();
                        if (taskNo == -1) {
                            throw new MissingInformationException();
                        }
                    tasks.deleteTask(userInput.getIndexFromCommand());
                    } catch (MissingInformationException e) {
                        ui.printIndexMissingError();
                    }
                    break;
                case "todo":
                    try {
                        String taskTitle = userInput.getTodoInfo();
                        if (taskTitle == null) {
                            throw new MissingInformationException();
                        }
                        tasks.createTodo(userInput.getTodoInfo(),true, false);
                    } catch (MissingInformationException e) {
                        ui.printTodoMissingError();
                    }
                    break;
                case "deadline":
                    try {
                        String[] separate_deadlines = userInput.getDeadlineInfo();
                        if (separate_deadlines.length < 2) {
                            throw new IllegalDeadlineFormatException();
                        }
                        tasks.createDeadline(separate_deadlines[0],separate_deadlines[1], true, false);
                    } catch (IllegalDeadlineFormatException e) {
                        ui.printDeadlineInputError();
                    }
                    break;
                case "event":
                    try {
                        String[] separate_events = userInput.getEventInfo();
                        if (separate_events.length < 3) {
                            throw new IllegalEventFormatException();
                        }
                        tasks.createEvent(separate_events[0],separate_events[1], separate_events[2], true, false);
                    } catch (IllegalEventFormatException e){
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
