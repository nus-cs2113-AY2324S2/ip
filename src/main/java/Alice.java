import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Alice {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;


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
        String keyword = input.substring(5); // assuming 'find ' is 5 characters
        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
        ui.showFoundTasks(foundTasks);
    }
    public static void main(String[] args) {
        new Alice("data/alice.txt").run();
    }
}
