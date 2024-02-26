package mimi;

import mimi.exceptions.MimiException;
import mimi.exceptions.MimiException.IncorrectFormat;
import mimi.exceptions.MimiException.FileCorrupted;

import mimi.helper.TaskList;
import mimi.helper.Ui;
import mimi.helper.Storage;


public class Duke {

    // DELIMITERS

    public final static String FILE_PATH = "data/mimi.logs";

    public final static String EVENT_FROM_DELIMITER = "/from";
    public final static String EVENT_TO_DELIMITER = "/to";


    private static Ui ui;
    private static Storage storage;

    private static TaskList tasks;



    public static void main(String[] args) {

        // Initial welcome message
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList();

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
            default:
                // raise invalid instruction
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
