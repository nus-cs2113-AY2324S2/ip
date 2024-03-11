package mimi;

import mimi.exceptions.MimiException;
import mimi.exceptions.MimiException.IncorrectFormat;
import mimi.exceptions.MimiException.FileCorrupted;

import mimi.helper.TaskList;
import mimi.helper.Ui;
import mimi.helper.Storage;

/**
 * MimiChat is a chatbot that helps you to keep track of your tasks.
 * It has the following features:
 * 1. Add a todo task (todo <task>)
 * 2. Add a deadline task (deadline <task> /by <date>)
 * 3. Add an event task (event <task> /from <start date> /to <end date>)
 * 4. List all tasks (list)
 * 5. Mark/Unmark a task as done (mark <task number> / unmark <task number>)
 * 6. Delete a task (delete <task number>)
 * 7. Find a task (find <keyword>)
 * 8. Exit the program (bye)
 *
 * The tasks are auto saved in a file called mimi.logs in the data folder after any changes are made.
 * The tasks are auto loaded from the mimi.logs file when the program starts.
 * Please ensure you have created the data folder in the same directory as the jar file.
 *
 * @author Justin
 * @version 0.2
 * @since 0.2
 */
public class Duke {


    public final static String FILE_PATH = "data/mimi.logs";

    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;

    public static void main(String[] args) {

        // Initial welcome message
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList();

        // Load file from the following path /data/mimi.logs
        try {
            storage.loadFile();
        } catch (FileCorrupted e) {
            System.out.println(e.getMessage());
        }

        while (ui.isRunning()) {
            String[] inputs = ui.getInput();
            switch (inputs[0]) {
            case "bye":
                ui.shutdownSequence();
                break;
            case "list":
                ui.listTasks(TaskList.getTaskList());
                break;
            case "mark":
                tasks.markTask(inputs);
                break;
            case "unmark":
                tasks.unmarkTasks(inputs);
                break;
            case "todo":
                tasks.addToDo(inputs);
                break;
            case "deadline":
                tasks.addDeadline(inputs);
                break;
            case "event":
                tasks.addEvent(inputs);
                break;
            case "delete":
                tasks.deleteTask(inputs);
                break;
            case "find":
                tasks.findTask(inputs);
                break;
            default:
                try {
                    throw new IncorrectFormat(MimiException.INCORRECT_INSTRUCTION_MSG);
                } catch (IncorrectFormat e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            storage.saveFile(TaskList.getTaskList(), FILE_PATH);
        }
    }


}
