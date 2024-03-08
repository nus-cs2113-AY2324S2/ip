package jake.parser;

import java.util.Scanner;
import jake.storage.Storage;
import jake.task.TaskList;
import jake.ui.Ui;

public class Parser {
    private static Ui ui = new Ui();
    private Storage storage;
    private TaskList tasks;

    public Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Continuously reads user inputs, until specificially told to stop using the command "Bye"
     * Based on user's input, will handle the command accordingly
     *
     */
    public void readInput() {
        Scanner myScanner = new Scanner(System.in);
        String userInput= "";
        do {
            userInput = myScanner.nextLine();
            String taskType; 
            // Check if the userInput contains a space, to handle inputs like "list" and "bye";
            if (userInput.contains(" ")) {
                taskType = userInput.substring(0, userInput.indexOf(" "));
            } else {
                taskType = userInput;
            }
            switch (taskType) {
            case "bye":
                ui.showGoodbye();
                break;
            case "list":
                tasks.listTasks();
                break;
            case "mark":
            case "unmark":
                tasks.toggleTask(userInput, taskType);
                storage.saveTasks(tasks);
                break;
            case "delete":
                tasks.deleteTask(userInput);
            case "todo":
            case "deadline":
            case "event":
                try {
                    tasks.addInputtedTask(userInput, taskType);
                    storage.saveTasks(tasks);
                    break;
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                    ui.showInvalidCommand();
                    break;
                } 
            case "find":
                String keyword = userInput.substring(userInput.indexOf(" ")+1);
                tasks.findTask(keyword);
                break;
            default:
                ui.showInvalidCommand();
            }
        } while (!userInput.equals("bye"));
        myScanner.close();
    }
}
