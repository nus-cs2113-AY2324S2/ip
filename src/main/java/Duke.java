import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    private static final Ui ui = new Ui();
    private static final TaskList tasks = new TaskList();

    public static void main(String[] args) {
        // Load tasks from file
        try {
            Storage.loadTasksFromFile(FILE_PATH, tasks);
        } catch (IOException e) {
            ui.showError("Error loading tasks from file: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        ui.showWelcomeMessage();

        while (true) {
            String input = scanner.nextLine();
            ui.showLine();

            try {
                String command = Parser.parseCommand(input);
                switch (command) {
                    case "bye":
                        ui.showGoodbyeMessage();
                        Storage.saveTasksToFile(tasks, FILE_PATH);
                        scanner.close();
                        return;
                    case "list":
                        ui.showTasks(tasks.getAllTasks());
                        break;
                    case "delete":
                        String[] deleteDetails = Parser.parseDetails(input);
                        int deleteIndex = Integer.parseInt(deleteDetails[1]) - 1;
                        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
                            throw new DukeException("Invalid task number.");
                        }
                        Task removedTask = tasks.removeTask(deleteIndex);
                        ui.showTaskRemovedMessage(removedTask, tasks.size());
                        break;
                    case "todo":
                        String[] todoDetails = Parser.parseDetails(input);
                        tasks.addTask(new Todo(todoDetails[1]));
                        ui.showTaskAddedMessage(tasks.getTask(tasks.size() - 1), tasks.size());
                        break;
                    case "deadline":
                        String[] deadlineDetails = Parser.parseDeadlineDetails(input);
                        tasks.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]));
                        ui.showTaskAddedMessage(tasks.getTask(tasks.size() - 1), tasks.size());
                        break;
                    case "event":
                        String[] eventDetails = Parser.parseEventDetails(input);
                        tasks.addTask(new Event(eventDetails[0], eventDetails[1], eventDetails[2]));
                        ui.showTaskAddedMessage(tasks.getTask(tasks.size() - 1), tasks.size());
                        break;
                    case "mark":
                        String[] markDetails = Parser.parseDetails(input);
                        int markIndex = Integer.parseInt(markDetails[1]) - 1;
                        if (markIndex < 0 || markIndex >= tasks.size()) {
                            throw new DukeException("Invalid task number.");
                        }
                        tasks.getTask(markIndex).markAsDone();
                        ui.showTaskMarkedDoneMessage(tasks.getTask(markIndex));
                        break;
                    case "unmark":
                        String[] unmarkDetails = Parser.parseDetails(input);
                        int unmarkIndex = Integer.parseInt(unmarkDetails[1]) - 1;
                        if (unmarkIndex < 0 || unmarkIndex >= tasks.size()) {
                            throw new DukeException("Invalid task number.");
                        }
                        tasks.getTask(unmarkIndex).markAsUndone();
                        ui.showTaskMarkedUndoneMessage(tasks.getTask(unmarkIndex));
                        break;
                    default:
                        throw new DukeException("Invalid command");
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("Invalid task number. Please enter a valid number.");
            } catch (IOException e) {
                ui.showError("Error saving tasks to file: " + e.getMessage());
            }
        }
    }
}
