import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 *  This class is the main entry point of the Alice chatbot. It initialises the application
 *  and handles user input commands to perform various task management operations such as
 *  adding, deleting, marking/ unmarking tasks, and finding tasks by their keyword.
 */

public class Alice {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs an instance of Alice and initialises the user interface, parser and storage.
     * It also attempts to load any saved tasks from storage.
     *
     * @param filePath The file path where the tasks are saved and loaded from.
     */
    public Alice(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the application and enters the command processing loop.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showGreeting();

        while (true) {
            String input = scanner.nextLine();

            try {
                if (input.equals("bye")) {
                    handleExit();
                } else if (input.startsWith("delete ")) {
                    handleDelete(input);
                } else if (input.equals("list")) {
                    ui.showTasks(tasks.getTasks());
                } else if (input.startsWith("todo ")) {
                    handleTodo(input);
                } else if (input.startsWith("deadline ")){
                    handleDeadline(input);
                } else if (input.startsWith("event ")){
                    handleEvent(input);
                } else if (input.startsWith("mark ") || input.startsWith("unmark ")){
                    handleMarkUnmark(input);
                } else if (input.equals("help")){
                    ui.showHelp();
                } else if(input.startsWith("find ")){
                    handleFind(input);
                }
                else {
                    ui.showInvalidInputMessage();
                }
            } catch (AliceException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("An error occurred while saving/loading tasks.");
            }
        }
    }

    private void handleExit() throws IOException {
        ui.showFarewell();
        storage.saveTasks(tasks.getTasks());
        System.exit(0);
    }

    private void handleDelete(String input) throws AliceException, IOException {
        int taskNumber = parser.parseTaskNumber(input);
        Task removedTask = tasks.deleteTask(taskNumber);
        ui.showTaskDeleted(removedTask, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }

    private void handleTodo(String input) throws AliceException, IOException {
        String description = parser.parseDescription(input);
        Todo newTask = new Todo(description);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }

    private void handleMarkUnmark(String input) throws AliceException, IOException {
        int taskIndex = parser.parseTaskNumber(input);
        Task task = tasks.getTask(taskIndex);
        if (input.startsWith("mark")) {
            task.markAsDone();
            ui.showMarkedTask(task);
        } else {
            task.markAsUndone();
            ui.showUnmarkedTask(task);
        }
        storage.saveTasks(tasks.getTasks());
    }

    private void handleDeadline(String input) throws AliceException, IOException {
        Parser.DeadlineData deadlineData = parser.parseDeadline(input);
        Deadline newDeadline = new Deadline(deadlineData.description, deadlineData.by);
        tasks.addTask(newDeadline);
        ui.showTaskAdded(newDeadline, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }


    private void handleEvent(String input) throws AliceException, IOException {
        Parser.EventData eventData = parser.parseEvent(input);
        Event newEvent = new Event(eventData.description, eventData.start, eventData.end);
        tasks.addTask(newEvent);
        ui.showTaskAdded(newEvent, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }

    private void handleFind(String input) throws AliceException {
        String keyword = input.substring(5).trim(); // Assuming input starts with "find "
        if (keyword.isEmpty()) {
            throw new AliceException("Keyword cannot be empty.");
        }

        Map<Integer, Task> matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }

    /**
     * Main method that serves as the entry point of the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Alice("data/alice.txt").run();
    }
}
