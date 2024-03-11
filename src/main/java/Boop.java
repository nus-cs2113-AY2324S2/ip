import java.io.FileNotFoundException;
import java.io.IOException;

public class Boop {
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    public Boop() {
        ui = new Ui(); //prints welcome message
        storage = new Storage();
        tasklist = new TaskList();
        try {
            storage.loadFromDisk(); //loads in current tasks and prints out number of tasks + end line
        } catch (FileNotFoundException e) {
            Ui.printError(e.getMessage());
        }
    }

    public void run() {
        boolean running = true;
        while(running) {
            String[] userInput =  ui.getUserInput();
            String command = userInput[0];
            switch (command) {
                case "bye":
                    try{
                        storage.saveToDisk(tasklist.getTasks());
                    } catch (IOException e) {
                        Ui.printError(e.getMessage());
                    }
                    running = false;
                    Ui.printBye();
                    break;
                case "list":
                    Ui.printTaskList(tasklist);
                    break;
                case "mark":
                    tasklist.mark(userInput);
                    break;
                case "unmark":
                    tasklist.unmark(userInput);
                    break;
                case "delete":
                    tasklist.delete(userInput);
                    break;
                case "todo":
                    tasklist.addTodo(userInput);
                    break;
                case "deadline":
                    tasklist.addDeadline(userInput);
                    break;
                case "event":
                    tasklist.addEvent(userInput);
                    break;
                case "find":
                    tasklist.find(userInput);
                    break;
                default:
                    Ui.unknownCommand();
                    break;
            }

        }

    }

    public static void main(String[] args) {
        new Boop().run();
    }
}